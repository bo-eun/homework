package it.back.back_app.board.controller;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.back.back_app.board.dto.ApiResponse;
import it.back.back_app.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getBoardList(
                @PageableDefault(size=10, page=0, sort="createDate", direction=Direction.DESC) Pageable pageable) throws Exception {
        log.info("------------ 게시글 가져오기 -------------");
        Map<String, Object> resultMap = boardService.getBoardList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

}
