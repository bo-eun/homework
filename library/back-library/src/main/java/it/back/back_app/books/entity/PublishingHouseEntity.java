package it.back.back_app.books.entity;

import java.util.List;

import it.back.back_app.common.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name="publishing_house")
public class PublishingHouseEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int publishingId;

    private String publishingName;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookEntity> books;
}
