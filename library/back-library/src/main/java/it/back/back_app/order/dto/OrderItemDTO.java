package it.back.back_app.order.dto;

import java.time.LocalDateTime;

import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.order.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OrderItemDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private int bookId;
        private int quantity;
        private int price;

        public static OrderItemEntity to(OrderItemDTO.Request dto) {
            return OrderItemEntity.builder()
                    // book은 service에서 처리
                    .quantity(dto.getQuantity())
                    .price(dto.getPrice())
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private int orderItemId;
        private int bookId;
        private String bookTitle;
        private String authorName;
        private String publishingHouseName;
        private int quantity;
        private int price;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public static Response of(OrderItemEntity entity) {
            return Response.builder()
                    .orderItemId(entity.getOrderItemId())
                    .bookId(entity.getBook().getBookId())
                    .bookTitle(entity.getBook().getBookName())
                    .authorName(entity.getBook().getAuthor().getAuthorName())
                    .publishingHouseName(entity.getBook().getPublishingHouse().getPublishingName())
                    .quantity(entity.getQuantity())
                    .price(entity.getPrice())
                    .createDate(entity.getCreateDate())
                    .updateDate(entity.getUpdateDate())
                    .build();
        }
    }
}
