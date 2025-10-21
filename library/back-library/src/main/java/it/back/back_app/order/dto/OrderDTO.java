package it.back.back_app.order.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.order.entity.OrderEntity;
import it.back.back_app.order.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OrderDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private Integer orderId;
        private String userId;
        private String shippingAddress;
        private String userName;
        private String phone;
        private List<OrderItemDTO.Request> orderItems;
        private int totalPrice;

        public static OrderEntity to(Request dto) {
            return OrderEntity.builder()
                    // user service에서 처리
                    .shippingAddress(dto.getShippingAddress())
                    .userName(dto.getUserName())
                    .phone(dto.getPhone())
                    .totalPrice(dto.getTotalPrice())
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private int orderId;
        private String userId;
        private LocalDateTime orderDate;
        private int totalPrice;
        private String shippingAddress;
        private List<OrderItemDTO.Response> orderItems;
        private String userName;
        private String phone;

        public static Response of(OrderEntity entity) {
            List<OrderItemDTO.Response> itemDTOs = entity.getOrderItems()
                .stream()
                .map(OrderItemDTO.Response::of)
                .collect(Collectors.toList());

            return Response.builder()
                    .orderId(entity.getOrderId())
                    .userId(entity.getUser().getUserId())
                    .orderDate(entity.getOrderDate())
                    .shippingAddress(entity.getShippingAddress())
                    .userName(entity.getUserName())
                    .phone(entity.getPhone())
                    .totalPrice(entity.getTotalPrice())
                    .orderItems(itemDTOs)
                    .build();
        }
    }
}