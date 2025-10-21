package it.back.back_app.admin.controller;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.back.back_app.admin.dto.PolicyDTO;
import it.back.back_app.admin.service.AdminService;
import it.back.back_app.board.dto.ApiResponse;
import it.back.back_app.books.dto.BookDTO;
import it.back.back_app.books.service.BookService;
import it.back.back_app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminRestController {

    private final BookService bookservice;
    private final UserService userService;
    private final AdminService adminService;

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

    @PutMapping("/book")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateBook(@ModelAttribute BookDTO.Request request) throws Exception {
        log.info("------------ 책 수정 -------------");
        Map<String, Object> resultMap = bookservice.updateBook(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }
    @DeleteMapping("/book/file/{imageId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deleteImage(@PathVariable("imageId") int imageId) throws Exception {
        log.info("------------ 책 이미지 삭제하기 -------------");
        Map<String, Object> resultMap = bookservice.deleteImage(imageId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deleteBook(@PathVariable(value="bookId") int bookId) throws Exception {

        log.info("------------ "+ bookId+" 번 글 삭제하기 -------------");
        Map<String, Object> resultMap = bookservice.deleteBook(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @GetMapping("/publishingHouse")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPublishingHouse() throws Exception  {
        log.info("------------ 출판사 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getPublishingHouse();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }
    
    @GetMapping("/author")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAuthorList() throws Exception  {
        log.info("------------ 저자 리스트 가져오기 -------------");
        Map<String, Object> resultMap = bookservice.getAuthorList();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }


    @GetMapping("/users")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUsers(@PageableDefault(size=10, page=0, sort="createDate", direction=Direction.DESC) Pageable pageable) throws Exception  {
        log.info("------------ 회원 리스트 가져오기 -------------");
        Map<String, Object> resultMap = userService.getUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @GetMapping("/policy")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPolicy() throws Exception  {
        log.info("------------ 교환/환불/반품 가져오기 -------------");
        Map<String, Object> resultMap = adminService.getPolicy();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }    

    @PostMapping("/policy")
    public ResponseEntity<ApiResponse<Map<String, Object>>> createPolicy(@RequestBody PolicyDTO request) throws Exception  {
        log.info("------------ 교환/환불/반품 설정 -------------");
        Map<String, Object> resultMap = adminService.createPolicy(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }    

    @DeleteMapping("/policy/{policyId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deletePolicy(@PathVariable int policyId) throws Exception  {
        log.info("------------ 교환/환불/반품 삭제 -------------");
        Map<String, Object> resultMap = adminService.deletePolicy(policyId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }   
}
