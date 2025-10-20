package it.back.back_app.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.back.back_app.cart.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer>{
    @Query("SELECT c FROM CartEntity c JOIN c.user u WHERE u.userId = :userId")
    List<CartEntity> findByUserId(@Param("userId") String userId);

    @Query("SELECT c FROM CartEntity c WHERE c.user.userId = :userId AND c.book.bookId = :bookId")
    Optional<CartEntity> findByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") int bookId);
}
