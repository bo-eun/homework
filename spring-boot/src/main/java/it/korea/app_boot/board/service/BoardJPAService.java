package it.korea.app_boot.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.korea.app_boot.board.dto.BoardDTO;
import it.korea.app_boot.board.entity.BoardEntity;
import it.korea.app_boot.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardJPAService {
    private final BoardRepository boardRepository;

    public Map<String, Object> getBoardList(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        // findAll() -> select * from board;
        Page<BoardEntity> pageObj = boardRepository.findAll(pageable);

        // List of Entity -> List of DTO
        // JDK16 메서드. toList()로 List를 만들면 불변값임
        List<BoardDTO.Response> list = pageObj.getContent().stream().map(BoardDTO.Response::of).toList();
        // List<BoardDTO.Response> list = pageObj.getContent().stream().map(BoardDTO.Response::of).collect(Collectors.toList()); // 가변객체

        resultMap.put("total", pageObj.getTotalElements());
        resultMap.put("contents", list);
        return resultMap;
    }
}
