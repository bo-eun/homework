package it.back.back_app.books.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.books.entity.BookType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


public class BookDTO {

    @Data
    @Builder
    public static class Request {
        private Integer bookId;
        @NotBlank(message = "도서명은 필수 항목입니다.")
        private String bookName;
        @NotBlank(message = "도서타입은 필수 항목입니다.")
        private String bookType;
        @NotNull(message = "가격은 필수 항목입니다.")
        @Positive(message = "가격은 0보다 커야 합니다.")
        private int price;
        @NotNull(message = "수량은 필수 항목입니다.")
        private int stock;
        @NotNull(message = "페이지수는 필수 항목입니다.")
        private int pageCount;
        private String shortIntro;
        private String intro;
        @NotNull(message = "출간일은 필수 항목입니다.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate publicationDate;
        private Boolean recommendationStatus;
        private MultipartFile bookImages;  // service에서 처리

        // service에서 처리
        @NotNull(message = "출판사는 필수 항목입니다.")
        private int publishingId;
        @NotNull(message = "작가는 필수 항목입니다.")
        private int authorId;

        public static Request of(BookEntity entity) {

            return Request.builder()
                    .bookId(entity.getBookId())
                    .bookName(entity.getBookName())
                    .bookType(entity.getBookType().name())
                    .publishingId(entity.getPublishingHouse().getPublishingId())
                    .authorId(entity.getAuthor().getAuthorId())
                    .price(entity.getPrice())
                    .stock(entity.getStock())
                    .pageCount(entity.getPageCount())
                    .shortIntro(entity.getShortIntro())
                    .intro(entity.getIntro())
                    .publicationDate(entity.getPublicationDate())
                    .recommendationStatus(entity.getRecommendationStatus())
                    .build();
        }        

        public static BookEntity to(BookDTO.Request request) {

            return BookEntity.builder()
                    .bookName(request.getBookName())
                    .bookType(BookType.valueOf(request.getBookType()))
                    .price(request.getPrice())
                    .stock(request.getStock())
                    .pageCount(request.getPageCount())
                    .shortIntro(request.getShortIntro())
                    .intro(request.getIntro())
                    .publicationDate(request.getPublicationDate())
                    .recommendationStatus(request.getRecommendationStatus())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class RecommendListResponse {
        private Integer bookId;
        private String bookName;
        private String authorNames;
        private String publishingHouseName;
        private List<BookImageDTO> bookImages;
        private Integer price;
        private Boolean recommendationStatus;
        private String shortIntro;
        private String intro;

        public static RecommendListResponse of(BookEntity entity) {
            List<BookImageDTO> images = entity.getBookImages().stream()
                .map(BookImageDTO::of)
                .toList();

            return RecommendListResponse.builder()
                    .bookId(entity.getBookId())
                    .bookName(entity.getBookName())
                    .authorNames(entity.getAuthor().getAuthorName())
                    .publishingHouseName(entity.getPublishingHouse().getPublishingName())
                    .price(entity.getPrice())
                    .bookImages(images)
                    .recommendationStatus(entity.getRecommendationStatus())
                    .shortIntro(entity.getShortIntro())
                    .intro(entity.getIntro())
                    .build();
        }
    }    

    @Getter
    @Builder
    public static class ListResponse {
        private Integer bookId;
        private String bookName;
        private String authorNames;
        private String publishingHouseName;
        private List<BookImageDTO> bookImages;
        private Integer price;
        private Boolean recommendationStatus;

        public static ListResponse of(BookEntity entity) {
            List<BookImageDTO> images = entity.getBookImages().stream()
                .map(BookImageDTO::of)
                .toList();

            return ListResponse.builder()
                    .bookId(entity.getBookId())
                    .bookName(entity.getBookName())
                    .authorNames(entity.getAuthor().getAuthorName())
                    .publishingHouseName(entity.getPublishingHouse().getPublishingName())
                    .price(entity.getPrice())
                    .bookImages(images)
                    .recommendationStatus(entity.getRecommendationStatus())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class DetailResponse {
        private Integer bookId;
        private String bookName;
        private String bookType;
        private int price;
        private int stock;
        private String shortIntro;
        private int pageCount;
        private String intro;
        private LocalDate publicationDate;
        private int publishingId;
        private String publishingHouseName;
        private int authorId;
        private String authorName;
        private Boolean recommendationStatus;
        private List<BookImageDTO> bookImages;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public static DetailResponse of(BookEntity entity) {
            List<BookImageDTO> images = entity.getBookImages().stream()
                    .map(BookImageDTO::of)
                    .toList();

            return DetailResponse.builder()
                    .bookId(entity.getBookId())
                    .bookName(entity.getBookName())
                    .bookType(entity.getBookType().name())
                    .price(entity.getPrice())
                    .stock(entity.getStock())
                    .shortIntro(entity.getShortIntro())
                    .pageCount(entity.getPageCount())
                    .intro(entity.getIntro())
                    .publicationDate(entity.getPublicationDate())
                    .publishingId(entity.getPublishingHouse().getPublishingId())
                    .publishingHouseName(entity.getPublishingHouse().getPublishingName())
                    .authorId(entity.getAuthor().getAuthorId())
                    .authorName(entity.getAuthor().getAuthorName())
                    .recommendationStatus(entity.getRecommendationStatus())
                    .bookImages(images)
                    .createDate(entity.getCreateDate())
                    .updateDate(entity.getUpdateDate())
                    .build();
        }
    }
}
