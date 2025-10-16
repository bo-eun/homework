package it.back.back_app.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.back.back_app.books.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}
