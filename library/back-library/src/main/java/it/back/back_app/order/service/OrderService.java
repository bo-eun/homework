package it.back.back_app.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import it.back.back_app.order.dto.OrderDTO;
import it.back.back_app.order.dto.OrderItemDTO;
import it.back.back_app.order.entity.OrderEntity;
import it.back.back_app.order.entity.OrderItemEntity;
import it.back.back_app.order.repository.OrderRepository;
import it.back.back_app.security.entity.UserEntity;
import it.back.back_app.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    // 주문 조회
    public Map<String,Object> getOrderByUser(String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<OrderEntity> entity = orderRepository.findByUserId(userId);
        List<OrderDTO.Response> list = entity.stream().map(OrderDTO.Response::of).toList();

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");
        resultMap.put("content", list);

        return resultMap;
    }

    // 주문 추가
    public Map<String,Object> addOrder(OrderDTO.Request request) {
        Map<String, Object> resultMap = new HashMap<>();
        OrderEntity entity = new OrderEntity();
        entity = OrderDTO.Request.to(request);

        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        entity.setUser(userEntity);

        // orderItem 넣기
        List<OrderItemDTO.Request> itemDTOList = request.getOrderItems();
        List<OrderItemEntity> itemEntityList = itemDTOList.stream().map(OrderItemDTO.Request::to).toList();
        entity.setOrderItems(itemEntityList);

        log.info("주문 상품 : " + request.getOrderItems());
        orderRepository.save(entity);

        OrderDTO.Response response = OrderDTO.Response.of(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");
        resultMap.put("content", response);

        return resultMap;
    }
}
