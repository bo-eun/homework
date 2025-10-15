package it.back.back_app.books.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.back.back_app.board.dto.ApiResponse;
import it.back.back_app.books.dto.BookDTO;
import it.back.back_app.books.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookRestController {

    private final BookService bookservice;

    @GetMapping("/book")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getBookList(
                @PageableDefault(size=10, page=0, sort="createDate", direction=Direction.DESC) Pageable pageable) throws Exception {
        log.info("------------ 책 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getBookList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @PostMapping("/book")
    public ResponseEntity<ApiResponse<Map<String, Object>>> createBook(@ModelAttribute BookDTO.Request request) throws Exception {
        log.info("------------ 책 등록 -------------");
        Map<String, Object> resultMap = bookservice.createBook(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @GetMapping("/publishingHouse")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPublishingHouse() throws Exception  {
        log.info("------------ 출판사 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getPublishingHouse();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }
    
    @GetMapping("/author")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAuthor() throws Exception  {
        log.info("------------ 저자 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getAuthor();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }
    
}
