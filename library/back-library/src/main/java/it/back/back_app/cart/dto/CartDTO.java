package it.back.back_app.cart.dto;

import java.time.LocalDateTime;
import java.util.List;

import it.back.back_app.books.dto.BookImageDTO;
import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.cart.entity.CartEntity;
import it.back.back_app.security.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CartDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private int cartId;
        private int bookId;
        private String bookTitle;
        private List<BookImageDTO> bookImages;
        private int price;
        private String authorName;
        private String publishingHouseName;
        private String userId;
        private int quantity;
        private int totalPrice;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;   

        public static CartDTO.Response of(CartEntity entity) {
            List<BookImageDTO> images = entity.getBook().getBookImages().stream()
                        .map(BookImageDTO::of)
                        .toList();

            return CartDTO.Response.builder()
                .cartId(entity.getCartId())
                .bookId(entity.getBook().getBookId())
                .bookTitle(entity.getBook().getBookName())
                .bookImages(images)
                .userId(entity.getUser().getUserId())
                .price(entity.getBook().getPrice())
                .quantity(entity.getQuantity())
                .authorName(entity.getBook().getAuthor().getAuthorName())
                .publishingHouseName(entity.getBook().getPublishingHouse().getPublishingName())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .build();
        }        
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Integer cartId;
        private int bookId;
        private String userId;
        private int quantity;

        public static CartDTO.Request of(CartEntity entity) {
            return CartDTO.Request.builder()
                .cartId(entity.getCartId())
                .bookId(entity.getBook().getBookId())
                .userId(entity.getUser().getUserId())
                .quantity(entity.getQuantity())
                .build();
        }      

        public static CartEntity to(CartDTO.Request dto) {
            // user와 book 은 service에서 넣어줌
            CartEntity.CartEntityBuilder builder = CartEntity.builder()
                .quantity(dto.getQuantity());

            // artId가 null이 아닐 때만 설정 (update용일 경우)
            if (dto.getCartId() != null) {
                builder.cartId(dto.getCartId());
            }

            return builder.build();
        }            
    }
}
