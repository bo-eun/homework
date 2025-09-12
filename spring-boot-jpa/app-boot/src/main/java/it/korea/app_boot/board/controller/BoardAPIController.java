package it.korea.app_boot.board.controller;

import org.springframework.web.bind.annotation.RestController;

import it.korea.app_boot.board.dto.BoardDTO;
import it.korea.app_boot.board.dto.BoardSearchDTO;
import it.korea.app_boot.board.service.BoardJPAService;
import it.korea.app_boot.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


// rest api용 클래스이기 때문에 RestController, view가 아닌 data가 return
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardAPIController {

    private final BoardService service;
    private final BoardJPAService jpaService;

    @GetMapping("/board/list")
    public ResponseEntity<Map<String, Object>> getBoardListData(BoardSearchDTO searchDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {

            resultMap = service.getBoardList(searchDTO);

        } catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }

        // HttpServletResponse + HttpStatus 결합 객체
        // 프론트와 백 환경이 다르면 백에서 오류가 났을 떄 (Exception 실행) 프론트에서는 알 수 없음
        // 그래서 ResponseEntity로 resultMap과 status(상태값)을 프론트에 전달함
        return new ResponseEntity<>(resultMap, status);
    }
    
    @GetMapping("/board/data")
    public ResponseEntity<Map<String, Object>> getBoardData(BoardSearchDTO searchDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        List<Sort.Order> sorts = new ArrayList<>();
        String[] sidxs = searchDTO.getSidx().split(",");
        String[] sords = searchDTO.getSord().split(",");

        for(int i = 0; i < sidxs.length; i++) {
            if(sords[i].equals("asc")) {
                sorts.add(new Sort.Order(Sort.Direction.ASC, sidxs[i]));
                sorts.add(new Sort.Order(Sort.Direction.DESC, sidxs[i]));
            }
        }

        // 현재 페이지, 가져올 개수, order by 객체 > 페이지 객체 만들기
        // sort가 여러개 일 때 직접 생성해서 사용
        Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize(), Sort.by(sorts));

        try {
            resultMap = jpaService.getBoardList(searchDTO, pageable);
        } catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @GetMapping("/board/{brdId}")
    // get방식일 때 데이터가 3개 이하면 @PathVariable로 데이터를 전송하는게 좋다.
    // get방식이어도 데이터가 많으면 @RequestParam 사용
    public ResponseEntity<Map<String, Object>> getBoard(@PathVariable(name="brdId") int brdId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        resultMap = jpaService.getBoard(brdId);

        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/board")
    public ResponseEntity<Map<String, Object>> writeBoard(@Valid @ModelAttribute BoardDTO.Request request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        resultMap = jpaService.writeBoard(request);

        return new ResponseEntity<>(resultMap, status);
    }

    @PutMapping("/board/{brdId}")
    public ResponseEntity<Map<String, Object>> updateBoard(@Valid @ModelAttribute BoardDTO.Request request,
                                                             @PathVariable int brdId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        resultMap = jpaService.updateBoard(request, brdId);
        
        return new ResponseEntity<>(resultMap, status);
    }

    // 파일삭제
    @DeleteMapping("/board/file/{bfId}")
    public ResponseEntity<Map<String, Object>> deleteFile(@PathVariable int bfId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        resultMap = jpaService.deleteFile(bfId);
        
        return new ResponseEntity<>(resultMap, status);
    }

    // 게시물 삭제
    @DeleteMapping("/board/{brdId}")
    public ResponseEntity<Map<String, Object>> deleteBoard(@PathVariable("brdId") int brdId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        resultMap = jpaService.deleteBoard(brdId);
        
        return new ResponseEntity<>(resultMap, status);
    }

    @GetMapping("/board/file/{bfId}")
    public ResponseEntity<Resource> downFile(@PathVariable("bfId") int bfId) throws Exception {

        ResponseEntity<Resource> resp = jpaService.downLoadFile(bfId);

        return resp;
    }
}
