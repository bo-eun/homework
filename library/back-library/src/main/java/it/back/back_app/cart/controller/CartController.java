package it.back.back_app.cart.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.back.back_app.board.dto.ApiResponse;
import it.back.back_app.cart.dto.CartDTO;
import it.back.back_app.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getCart(@PathVariable String userId) {
        log.info("------------ 장바구니 리스트 가져오기 -------------");
        Map<String, Object> resultMap = cartService.getCartByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> addCart(@RequestBody CartDTO.Request cartDTO) {
        log.info("------------ 장바구니 담기 -------------");
        Map<String, Object> resultMap = cartService.addCart(cartDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateQuantity(@RequestBody CartDTO.Request request) {
        log.info("------------ 수량 번경 -------------");
        Map<String, Object> resultMap = cartService.updateQuantity(request);                                                    
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));                                                    
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> deleteCart(@PathVariable int cartId) {
        Map<String, Object> resultMap = cartService.deleteCart(cartId);
        
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));    
    }
}
