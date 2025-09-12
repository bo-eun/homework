package it.korea.app_boot.gallery.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.korea.app_boot.gallery.dto.GalleryRequest;
import it.korea.app_boot.gallery.service.GalleryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GalleryAPIController {

    private final GalleryService galleryService;

    @GetMapping("/gal")
    public ResponseEntity<Map<String, Object>> getGalleryList(
        // page, size, sort default값 지정
        // entity 기준 변수명 사용
        @PageableDefault(page=0, size=10, sort="createDate", direction=Sort.Direction.DESC) Pageable pageable) {

        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = galleryService.getGalleryList(pageable);
        } catch(Exception e) {
            
        }
    
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/gal")
    public ResponseEntity<Map<String, Object>> writeGallery(@Valid @ModelAttribute GalleryRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try {
            galleryService.addGallery(request);
            resultMap.put("resultCode", 200);
            resultMap.put("resultMessage", "OK");
        } catch(Exception e) {
            // 예외 발생 시 공통 모듈을 실행하기 위해 예외를 던진다.
            throw new Exception(e.getMessage() == null ? "이미지 등록 실패" : e.getMessage());
        }

        return new ResponseEntity<>(resultMap, status);
    }
}
