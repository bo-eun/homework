package it.back.back_app.books.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="book_image")
public class BookImageEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int imageId;
    
    private String fileName;
    private String storedName;
    private String filePath;
    private Long fileSize; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;
}
