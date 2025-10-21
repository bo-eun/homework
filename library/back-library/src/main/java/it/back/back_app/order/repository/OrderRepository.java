package it.back.back_app.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.back.back_app.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("SELECT o FROM OrderEntity o JOIN o.user u WHERE u.userId = :userId")
    List<OrderEntity> findByUserId(@Param("userId") String userId);
}
