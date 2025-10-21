package it.back.back_app.order.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.back.back_app.board.dto.ApiResponse;
import it.back.back_app.order.dto.OrderDTO;
import it.back.back_app.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getOrder(@PathVariable String userId) {
        log.info("------------ 주문 리스트 가져오기 -------------");
        Map<String, Object> resultMap = orderService.getOrderByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> addOrder(@RequestBody OrderDTO.Request orderDTO) {
        log.info("------------ 주문 하기 -------------");
        Map<String, Object> resultMap = orderService.addOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(resultMap));
    }
}
