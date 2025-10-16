package it.back.back_app.books.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.books.entity.BookType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDTO {
    private int bookId;
    private String bookName;
    private String bookType;
    private Integer price;
    private Integer stock;
    private String shortIntro;
    private String intro;
    private LocalDate publicationDate;
    private String publishingHouseName;
    private String authorNames;
    private List<BookImageDTO> bookImages;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static BookDTO of(BookEntity entity) {

        String authorNames = entity.getAuthor().getAuthorName();
        List<BookImageDTO> fileList = entity.getBookImages().stream().map(BookImageDTO::of).toList();

        return BookDTO.builder()
                        .bookId(entity.getBookId())
                        .bookName(entity.getBookName())
                        .bookType(entity.getBookType().name())
                        .price(entity.getPrice())
                        .stock(entity.getStock())
                        .intro(entity.getIntro())
                        .shortIntro(entity.getShortIntro())
                        .publishingHouseName(entity.getPublishingHouse().getPublishingName())
                        .authorNames(authorNames)
                        .bookImages(fileList)
                        .createDate(entity.getCreateDate())
                        .updateDate(entity.getUpdateDate())
                        .build();
    }

    public LocalDateTime getModifiedDate() {
        return this.updateDate == null ? this.createDate : this.updateDate;
    }

    @Data
	public static class Request{
        private int bookId;
        @NotBlank(message = "도서명은 필수 항목입니다.")
        private String bookName;
        @NotBlank(message = "도서타입은 필수 항목입니다.")
        private String bookType;
        @NotNull(message = "가격은 필수 항목입니다.")
        @Positive(message = "가격은 0보다 커야 합니다.")
        private int price;
        @NotNull(message = "수량은 필수 항목입니다.")
        private int stock;
        private String shortIntro;
        private String intro;
        @NotNull(message = "출판사는 필수 항목입니다.")
        private int publishingId;
        @NotNull(message = "작가는 필수 항목입니다.")
        private int authorId;

		private MultipartFile bookImages;    
	}


    public static BookEntity to(BookDTO.Request request) {

    return BookEntity.builder()
            .bookId(request.getBookId())
            .bookName(request.getBookName())
            .bookType(request.getBookType() != null ? BookType.valueOf(request.getBookType()) : null)
            .price(request.getPrice())
            .stock(request.getStock())
            .shortIntro(request.getShortIntro())
            .intro(request.getIntro())
            .build();
}  
}
