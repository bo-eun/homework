package it.back.back_app.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.back.back_app.books.dto.BookDTO;
import it.back.back_app.books.entity.BookEntity;
import it.back.back_app.security.entity.UserEntity;
import it.back.back_app.security.repository.UserRepository;
import it.back.back_app.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder  passwordEncoder;

    @Transactional
    public Map<String, Object> createUser(UserDTO.Detail dto) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        if(dto.getUserId() == null || dto.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("사용자 ID는 필수 입력값입니다.");
        }

        if (userRepository.existsByUserId(dto.getUserId())) {
        throw new RuntimeException("이미 존재하는 사용자 ID입니다.");
    }
         
        UserEntity entity = UserDTO.Detail.to(dto);
        // 비밀번호 암호화
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

        return resultMap;
    }

    @Transactional
    public Map<String, Object> updateUser(UserDTO.Detail dto) throws Exception {
         Map<String, Object> resultMap = new HashMap<>();
         
        UserEntity entity = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        entity.setName(dto.getName());
        
        // 변경할 패스워드가 있다면 암호화 처리
        if(StringUtils.isBlank((dto.getPassword()))) {
            // 비밀번호 암호화
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        entity.setAddress(dto.getAddress());
        entity.setAddressDetail(dto.getAddressDetail() != null ? 
                            dto.getAddressDetail() : entity.getAddressDetail());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        userRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

         return resultMap;
    }


    @Transactional(readOnly = true)
    public Map<String,Object> getUsers(Pageable pageable) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<UserEntity> entity = userRepository.findAll();

        List<UserDTO.Response> list = entity.stream()
                    .map(UserDTO.Response::of)
                    .toList();        
                    
        resultMap.put("content", list);
        return resultMap;
    }    
}