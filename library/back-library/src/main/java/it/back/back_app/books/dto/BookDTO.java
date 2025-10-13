package it.back.back_app.books.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import it.back.back_app.books.entity.AuthorEntity;
import it.back.back_app.books.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private int price;
    private int stock;
    private String shortIntro;
    private String intro;
    private LocalDate publicationDate;
    private String publishingHouseName;
    private List<String> authorNames;
    private List<String> bookImageUrls;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static BookDTO of(BookEntity entity) {
        return BookDTO.builder()
                        .bookId(entity.getBookId())
                        .bookName(entity.getBookName())
                        .bookType(entity.getBookType().name())
                        .price(entity.getPrice())
                        .stock(entity.getStock())
                        .intro(entity.getIntro())
                        .shortIntro(entity.getShortIntro())
                        .publicationDate(entity.getPublicationDate())
                        .publishingHouseName(entity.getPublishingHouse().getPublishingName())
                        .authorNames(entity.getAuthors().stream()
                            .map(AuthorEntity::getAuthorName)
                            .collect(Collectors.toList())
                        )
                        .bookImageUrls(entity.getBookImages().stream()
                            .map(img -> img.getFilePath() + "/" + img.getStoredName())
                            .collect(Collectors.toList())
                        )
                        .createDate(entity.getCreateDate())
                        .updateDate(entity.getUpdateDate())
                        .build();
    }

    public LocalDateTime getModifiedDate() {
        return this.updateDate == null ? this.createDate : this.updateDate;
    }

}
