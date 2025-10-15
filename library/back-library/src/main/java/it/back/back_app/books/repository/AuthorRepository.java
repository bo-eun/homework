package it.back.back_app.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.back.back_app.books.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

}
