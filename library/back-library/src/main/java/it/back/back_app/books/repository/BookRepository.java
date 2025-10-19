package it.back.back_app.books.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.back.back_app.books.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    /* 책 정보 가져오기 */
    @Query("SELECT b FROM BookEntity b " +
           "JOIN FETCH b.author a " +
           "JOIN FETCH b.publishingHouse p " +
           "WHERE b.bookId = :bookId")
    Optional<BookEntity> findByIdWithAuthorAndPublishing(@Param("bookId") Integer bookId);
    
    

    // 추천 상태(true)인 도서 중 최신도서 10개
    List<BookEntity> findTop10ByRecommendationStatusTrueOrderByCreateDateDesc();

    // 신상 도서 10개
    List<BookEntity> findTop10ByOrderByCreateDateDesc();
 

    // 판매량 순 Top 10
    @Query(value = """
        SELECT b.* 
        FROM book b
        LEFT JOIN order_items oi ON b.book_id = oi.book_id
        GROUP BY b.book_id
        ORDER BY SUM(oi.quantity) DESC
        """, nativeQuery = true)
    List<BookEntity> findTopSellingBooks();

    // 판매량 순 도서 페이징 처리
    @Query(value = """
        SELECT b.* 
        FROM book b
        LEFT JOIN order_items oi ON b.book_id = oi.book_id
        GROUP BY b.book_id
        ORDER BY SUM(oi.quantity) DESC
        """, nativeQuery = true)
    Page<BookEntity> findAllOrderBySalesDesc(Pageable pageable);
}
