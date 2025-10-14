package it.back.back_app.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.back.back_app.admin.dto.AdminUserSearchDTO;
import it.back.back_app.admin.repository.AdminUserRepository;
import it.back.back_app.admin.repository.AdminUserSearchSpecification;
import it.back.back_app.security.entity.UserEntity;
import it.back.back_app.security.repository.UserRepository;
import it.back.back_app.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder  passwordEncoder;

    /* 전체 유저 리스트 */
    @Transactional
    public Map<String, Object> getUserList(Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = adminUserRepository.findAll(pageable);

        List<UserDTO.Response> list = pageList.getContent().stream().map(UserDTO.Response::of).toList();

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("content", list);
        resultMap.put("page", pageList.getNumber());

        return resultMap;
    }

    /* 검색한 유저 리스트 */
    @Transactional
    public Map<String, Object> getUserList(Pageable pageable, AdminUserSearchDTO searchDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = null;

        AdminUserSearchSpecification adminUserSearchSpecification = new AdminUserSearchSpecification(searchDTO);
   
        pageList = adminUserRepository.findAll(adminUserSearchSpecification);

        List<UserDTO.Response> list = pageList.getContent().stream().map(UserDTO.Response::of).toList();

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("content", list);
        resultMap.put("page", pageList.getNumber());

        return resultMap;
    }

    @Transactional
    public UserDTO getUser(String userId) throws Exception {
        AdminUserProjection user = adminUserRepository.getUserById(userId).orElseThrow(() -> new RuntimeException("사용자 없음"));

        return UserDTO.of(user);
    }

    /* 유저 정보 수정 */
    @Transactional
    public Map<String, Object> updateUser(UserDTO.Detail dto) throws Exception {
         Map<String, Object> resultMap = new HashMap<>();
         
        UserEntity entity = adminUserRepository.findById(dto.getUserId())
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

        adminUserRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

         return resultMap;
    }

}
