package it.back.back_app.books.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.back.back_app.common.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="author")
public class AuthorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int authorId;

    private String authorName;
    private String intro;
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    private LocalDate birthDate;
    
    @OneToMany(mappedBy = "author")
    private List<BookEntity> books = new ArrayList<>();

}
