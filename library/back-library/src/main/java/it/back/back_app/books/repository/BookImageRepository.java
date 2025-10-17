package it.back.back_app.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.back.back_app.books.entity.BookImageEntity;

public interface BookImageRepository extends JpaRepository<BookImageEntity, Integer> {

}
