package it.back.back_app.books.controller;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.back.back_app.board.dto.ApiResponse;
import it.back.back_app.books.dto.BookDTO;
import it.back.back_app.books.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookRestController {

    private final BookService bookservice;

    @GetMapping("/book/recommend")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getRecommendMain() throws Exception {
        log.info("------------ 메인 추천책 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getRecommendMain();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @GetMapping("/book/newList")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getNewMain() throws Exception {
        log.info("------------ 메인 신상 책 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getNewMain();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }  

    @GetMapping("/book/bestList")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getBestMain() throws Exception {
        log.info("------------ 메인 베스트 책 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getBestMain();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }       



    @GetMapping("/book/list/recommend")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getRecommendList(
                @PageableDefault(size=10, page=0, sort="createDate", direction=Direction.DESC) Pageable pageable) throws Exception {
        log.info("------------ 추천책 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getRecommendList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @GetMapping("/book/list/newList")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getNewList(
                @PageableDefault(size=10, page=0, sort="createDate", direction=Direction.DESC) Pageable pageable) throws Exception {
        log.info("------------ 신상 책 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getNewList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }    

    @GetMapping("/book/list/bestList")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getBestList(
                @PageableDefault(size=10, page=0, sort="createDate", direction=Direction.DESC) Pageable pageable) throws Exception {
        log.info("------------ 베스트 책 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getBestList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }  

    @GetMapping("book/{bookId}")
    public ResponseEntity<ApiResponse<BookDTO.DetailResponse>> getBookList(@PathVariable(value="bookId") int bookId) throws Exception {
        log.info("------------ "+ bookId+" 번 글 가져오기 -------------");
        BookDTO.DetailResponse detail = bookservice.getBook(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(detail));
    }
    
}
