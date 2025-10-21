package it.back.back_app.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.back.back_app.order.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {

}
