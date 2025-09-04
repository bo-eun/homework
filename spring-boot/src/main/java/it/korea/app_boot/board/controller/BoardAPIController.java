package it.korea.app_boot.board.controller;

import org.springframework.web.bind.annotation.RestController;

import it.korea.app_boot.board.dto.BoardSearchDTO;
import it.korea.app_boot.board.service.BoardJPAService;
import it.korea.app_boot.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

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
            resultMap = jpaService.getBoardList(pageable);
        } catch(Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultMap, status);
    }
}
