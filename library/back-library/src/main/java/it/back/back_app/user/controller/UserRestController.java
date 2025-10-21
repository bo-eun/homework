package it.back.back_app.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.back.back_app.board.dto.ApiResponse;
import it.back.back_app.user.dto.UserDTO;
import it.back.back_app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class UserRestController {

    private final UserService userService;
    
    /* 회원가입 */
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Map<String, Object>>> createUser(@RequestBody UserDTO.Detail userDTO) throws Exception {
        try {
            log.info("------------ 회원 가입하기 -------------");
            log.info("phone : {}", userDTO.getPhone());
            Map<String, Object> resultMap = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
        } catch (RuntimeException e) {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("resultCode", 400);
            errorMap.put("resultMsg", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(409, errorMap));
        }
    }



}
