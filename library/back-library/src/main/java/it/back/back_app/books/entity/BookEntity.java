package it.back.back_app.books.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.back.back_app.common.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name="book")
public class BookEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer bookId;

    private String bookName;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    private int price;
    private int stock;
    private int pageCount;
    private String intro;
    private String shortIntro;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate publicationDate;
    private Boolean recommendationStatus;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publishing_id")
    private PublishingHouseEntity publishingHouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @Builder.Default
    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookImageEntity> bookImages = new HashSet<>();

    public void addFiles(BookImageEntity entity) {
        if(bookImages == null) this.bookImages = new HashSet<>();
        entity.setBook(this);
        bookImages.add(entity);
    }
}
