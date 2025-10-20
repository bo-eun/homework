package it.back.back_app.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.books.repository.BookRepository;
import it.back.back_app.cart.dto.CartDTO;
import it.back.back_app.cart.entity.CartEntity;
import it.back.back_app.cart.repository.CartRepository;
import it.back.back_app.security.entity.UserEntity;
import it.back.back_app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {
    private final CartRepository cartRepository;   
    private final UserRepository userRepository; 
    private final BookRepository bookRepository; 

    // 장바구니 조회
    public Map<String,Object> getCartByUser(String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<CartEntity> entity = cartRepository.findByUserId(userId);
        List<CartDTO.Response> list = entity.stream().map(CartDTO.Response::of).toList();

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");
        resultMap.put("content", list);

        return resultMap;
    }

    // 장바구니 추가
    public Map<String,Object> addCart(CartDTO.Request request) {
        Map<String, Object> resultMap = new HashMap<>();
        CartEntity entity = new CartEntity();
        entity = CartDTO.Request.to(request);

        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        BookEntity bookEntity = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));

        entity.setBook(bookEntity);
        entity.setUser(userEntity);

        cartRepository.save(entity);

        CartDTO.Response response = CartDTO.Response.of(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");
        resultMap.put("content", response);

        return resultMap;
    }

    // 장바구니 삭제
    public Map<String,Object> deleteCart(int cartId) {
        Map<String, Object> resultMap = new HashMap<>();

        cartRepository.deleteById(cartId);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }

    // 수량 변경
    public Map<String,Object> updateQuantity(CartDTO.Request request) {
        Map<String, Object> resultMap = new HashMap<>();
        CartEntity entity = cartRepository.findByUserIdAndBookId(request.getUserId(), request.getBookId())
            .orElseThrow(() -> new RuntimeException("장바구니 아이템 찾을 수 없음"));

        entity.setQuantity(request.getQuantity());
    
        CartDTO.Response response = CartDTO.Response.of(entity);

        resultMap.put("content", response);
        return resultMap;
    }    
}
