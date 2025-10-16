package it.back.back_app.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import it.back.back_app.board.dto.BoardDTO;
import it.back.back_app.board.dto.BoardFileDTO;
import it.back.back_app.board.entity.BoardEntity;
import it.back.back_app.board.entity.BoardFileEntity;
import it.back.back_app.board.repository.BoardFileRepository;
import it.back.back_app.board.repository.BoardRepository;
import it.back.back_app.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileUtils fileUtils;
    private final BoardFileRepository boardFileRepository;

    @Value("${server.file.review.path}")
    String filePath;

    @Transactional(readOnly = true)
    public Map<String, Object> getBoardList(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Page<BoardEntity> boardList = boardRepository.findAll(pageable);

        // toList()로 불변객체를 만들어 데이터 변경을 막음
        List<BoardDTO.Response> list = boardList.getContent().stream().map(BoardDTO.Response::of).toList();

        resultMap.put("total", boardList.getTotalElements());
        resultMap.put("page", boardList.getNumber());
        resultMap.put("content", list);

        return resultMap;
    }

    @Transactional(readOnly = true)
    public BoardDTO.Detail getBoard(int brdId) {
        BoardEntity entity = boardRepository.getBoard(brdId).orElseThrow(() -> new RuntimeException("찾는 게시글이 없음"));
        
        return BoardDTO.Detail.of(entity);
    }

    @Transactional
    public Map<String, Object> writeBoard(BoardDTO.Request request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        BoardEntity entity = new BoardEntity();

        entity.setTitle(request.getTitle());
        entity.setContents(request.getContents());
        entity.setWriter("admin");

        // 기존 파일 삭제
        if(request.getFile() != null && !request.getFile().isEmpty()) {
            Map<String, Object> fileMap = fileUtils.uploadFiles(request.getFile(), filePath);

            if(fileMap != null) {
                BoardFileEntity fileEntity = new BoardFileEntity();
                fileEntity.setFileName(fileMap.get("fileName").toString());
                fileEntity.setStoredName(fileMap.get("storedFileName").toString());
                fileEntity.setFilePath(fileMap.get("filePath").toString());
                fileEntity.setFileSize(request.getFile().getSize());

                entity.addFiles(fileEntity);
            } else {
                throw new RuntimeException("파일 업로드 오류");
            }
        }

        boardRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }



    @Transactional
    public Map<String, Object> updateBoard(BoardDTO.Request request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        BoardEntity entity = boardRepository.getBoard(request.getBrdId()).orElseThrow(() -> new RuntimeException("찾는 게시글이 없음"));

        BoardDTO.Detail detail = BoardDTO.Detail.of(entity);

        // 첨부 파일이 있다면
        if(request.getFile() != null && !request.getFile().isEmpty()) {
            Map<String, Object> fileMap = fileUtils.uploadFiles(request.getFile(), filePath);
            if(fileMap != null) {
                BoardFileEntity fileEntity = new BoardFileEntity();
                fileEntity.setFileName(fileMap.get("fileName").toString());
                fileEntity.setStoredName(fileMap.get("storedFileName").toString());
                fileEntity.setFilePath(fileMap.get("filePath").toString());
                fileEntity.setFileSize(request.getFile().getSize());

                // 수정한 첨부 파일 추가
                entity.getFileList().clear();
                entity.addFiles(fileEntity);
            } else {
                throw new RuntimeException("파일 업로드 오류");
            }

        }

        entity.setTitle(request.getTitle());
        entity.setContents(request.getContents());

        // 게시글 업데이트
        boardRepository.save(entity);

        // 기존 파일 삭제
        if(request.getFile() != null && !request.getFile().isEmpty()) {
            if(detail.getFileList() != null && !detail.getFileList().isEmpty()) {
                for(BoardFileDTO file : detail.getFileList()) {
                    String oldFilePath = filePath + file.getStoredName();
                    fileUtils.deleteFile(oldFilePath);
                }
            }
        }

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }

    @Transactional
    public Map<String, Object> deleteFile(int bfId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        BoardFileEntity fileEntity = boardFileRepository.findById(bfId).orElseThrow(() -> new RuntimeException("파일이 존재하지 않습니다."));

        String oldFilePath = fileEntity.getFilePath() + fileEntity.getStoredName();

        // 실제 저장된 파일 삭제
        fileUtils.deleteFile(oldFilePath);
        // DB에 저장된 파일 정보 삭제
        boardFileRepository.delete(fileEntity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }

    @Transactional
    public Map<String, Object> deleteBoard(int brdId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        BoardEntity entity = boardRepository.getBoard(brdId).orElseThrow(() -> new RuntimeException("찾는 게시글이 없음"));

        // dto로 변경
        BoardDTO.Detail detail = BoardDTO.Detail.of(entity);
        // 게시글 삭제 > 고아파일 정책 실행해서 file 디비도 같이 지워짐
        boardRepository.delete(entity);

        // 기존 파일 삭제
        if(detail.getFileList() != null && !detail.getFileList().isEmpty()) {
            for(BoardFileDTO file : detail.getFileList()) {
                String oldFilePath = filePath + file.getStoredName();
                fileUtils.deleteFile(oldFilePath);
            }
        }

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }

}
