package it.korea.app_boot.admin.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.korea.app_boot.admin.dto.AdminUserDTO;
import it.korea.app_boot.admin.dto.AdminUserSearchDTO;
import it.korea.app_boot.admin.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class AdminUserAPIController {

    private final AdminUserService userService;

    @GetMapping("/admin/user")
    public ResponseEntity<Map<String, Object>> getUserList(
        @PageableDefault(page = 0, size = 10, sort = "createDate", direction= Direction.DESC) Pageable pageable,
        AdminUserSearchDTO searchDTO) throws Exception {
        
        Map<String, Object> resultMap = userService.getUserList(pageable, searchDTO);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/admin/user") 
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody AdminUserDTO dto) {
        Map<String, Object>  resultMap = null;

        try {
            resultMap = userService.createUser(dto);
        } catch(Exception e) { 
            log.error("사용자 등록 오류");
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/admin/update") 
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody AdminUserDTO dto) {
        Map<String, Object>  resultMap = null;

        try {
            resultMap = userService.updateUser(dto);
        } catch(Exception e) { 
            log.error("사용자 수정 오류");
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping("/admin/user/{userId}") 
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String userId) {
        Map<String, Object>  resultMap = null;

        try {
            resultMap = userService.useYnUpdate(userId);
        } catch(Exception e) { 
            log.error("사용자 사용여부 수정 오류");
            e.printStackTrace();
        }

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
