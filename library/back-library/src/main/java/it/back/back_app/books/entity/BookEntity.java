package it.back.back_app.books.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="book")
public class BookEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bookId;

    private String bookName;
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    private int price;
    private int stock;
    private String intro;
    private String shortIntro;
    private LocalDate publicationDate;

    // book_author은 book_id, author_id만 있는 순수 연결 테이블이기 때문에 entity를 만들지 않아도 됨
    // @ManyToMany가 자동으로 매핑해준다. 
    // 다른 컬럼이 있을 경우 entity를 만들어야함
    @ManyToMany
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> authors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publishing_id")
    private PublishingHouseEntity publishingHouse;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookImageEntity> bookImages;
}
