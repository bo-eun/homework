// package it.back.back_app.board.controller;

// import java.util.Map;

// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort.Direction;
// import org.springframework.data.web.PageableDefault;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import it.back.back_app.board.dto.ApiResponse;
// import it.back.back_app.board.dto.BoardDTO;
// import it.back.back_app.board.service.BoardService;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @RestController
// @Slf4j
// @RequiredArgsConstructor
// @RequestMapping("/api/v1")
// public class BoardRestController {

//     private final BoardService boardService;

//     @GetMapping("/board")
//     public ResponseEntity<ApiResponse<Map<String, Object>>> getBoardList(
//                 @PageableDefault(size=10, page=0, sort="createDate", direction=Direction.DESC) Pageable pageable) throws Exception {
//         log.info("------------ 게시글 가져오기 -------------");
//         Map<String, Object> resultMap = boardService.getBoardList(pageable);
//         return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
//     }

//     @GetMapping("/board/{brdId}")
//     public ResponseEntity<ApiResponse<BoardDTO.Detail>> getBoard(@PathVariable(value="brdId") int brdId) throws Exception {

//         log.info("------------ "+ brdId+" 번 글 가져오기 -------------");
//         BoardDTO.Detail detail = boardService.getBoard(brdId);

//         return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(detail));
//     }

//     @PostMapping("/board")
//     public ResponseEntity<ApiResponse<Map<String, Object>>> writeBoard(BoardDTO.Request request) throws Exception {
//         log.info("------------ 게시글 등록하기 -------------");
//         Map<String, Object> resultMap = boardService.writeBoard(request);
//         return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
//     }

//     @PutMapping("/board")
//     public ResponseEntity<ApiResponse<Map<String, Object>>> updateBoard(BoardDTO.Request request) throws Exception {
//         log.info("------------ 게시글 수정하기 -------------");
//         Map<String, Object> resultMap = boardService.updateBoard(request);
//         return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
//     }

//     @DeleteMapping("/board/file/{bfId}")
//     public ResponseEntity<ApiResponse<Map<String, Object>>> deleteFile(@PathVariable("bfId") int bfId) throws Exception {
//         log.info("------------ 파일 삭제하기 -------------");
//         Map<String, Object> resultMap = boardService.deleteFile(bfId);
//         return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
//     }

//     @DeleteMapping("/board/{brdId}")
//     public ResponseEntity<ApiResponse<Map<String, Object>>> deleteBoard(@PathVariable(value="brdId") int brdId) throws Exception {

//         log.info("------------ "+ brdId+" 번 글 삭제하기 -------------");
//         Map<String, Object> resultMap = boardService.deleteBoard(brdId);

//         return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
//     }

// }
