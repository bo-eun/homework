package it.exam.backoffice.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.exam.backoffice.admin.dto.AdminUserDTO;
import it.exam.backoffice.admin.dto.AdminUserProjection;
import it.exam.backoffice.admin.dto.AdminUserSearchDTO;
import it.exam.backoffice.security.entity.UserEntity;
import it.exam.backoffice.security.entity.UserRoleEntity;
import it.exam.backoffice.security.repository.UserRepository;
import it.exam.backoffice.security.repository.UserRoleRepository;
import it.exam.backoffice.security.repository.UserSearchSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final PasswordEncoder  passwordEncoder;

    @Transactional
    public Map<String, Object> getUserList(Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = userRepository.findAll(pageable);

        List<AdminUserDTO> list = pageList.getContent().stream().map(AdminUserDTO::of).toList();

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("content", list);

        return resultMap;
    }

    @Transactional
    public Map<String, Object> getUserList(Pageable pageable, AdminUserSearchDTO searchDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = null;

        UserSearchSpecification userSearchSpecification = new UserSearchSpecification(searchDTO);
   
        pageList = userRepository.findAll(userSearchSpecification, pageable);

        List<AdminUserDTO> list = pageList.getContent().stream().map(AdminUserDTO::of).toList();

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("page", 0);
        resultMap.put("content", list);

        return resultMap;
    }

    @Transactional
    public AdminUserDTO getUser(String userId) throws Exception {
        AdminUserProjection user = userRepository.getUserById(userId).orElseThrow(() -> new RuntimeException("사용자 없음"));

        return AdminUserDTO.of(user);
    }


    @Transactional
    public Map<String, Object> createUser(AdminUserDTO dto) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        UserEntity checkUser = userRepository.findById(dto.getUserId()).orElse(null);

        if(checkUser != null) {
            throw new RuntimeException("해당 사용자가 존재함");
        }
         
        UserEntity entity = AdminUserDTO.to(dto);
         // Role 조회
        UserRoleEntity role = roleRepository.findById(dto.getUserRole())
            .orElseThrow(() -> new RuntimeException("Role not found"));

        // 비밀번호 암호화
        entity.setPasswd(passwordEncoder.encode(dto.getPasswd()));
        entity.setRole(role);

        userRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

         return resultMap;
    }

    @Transactional
    public Map<String, Object> updateUser(AdminUserDTO dto) throws Exception {
         Map<String, Object> resultMap = new HashMap<>();
         
        UserRoleEntity role = roleRepository.findById(dto.getUserRole())
            .orElseThrow(() -> new RuntimeException("Role not found"));
        UserEntity entity = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
            entity.setUserName(dto.getUserName());
            
            // 변경할 패스워드가 있다면 암호화 처리
            if(StringUtils.isBlank((dto.getPasswd()))) {
                // 비밀번호 암호화
                entity.setPasswd(passwordEncoder.encode(dto.getPasswd()));
            }

            entity.setAddr(dto.getAddr());
            entity.setAddrDetail(dto.getAddrDetail() != null ? 
                                dto.getAddrDetail() : entity.getAddrDetail());
            entity.setEmail(dto.getEmail());
            entity.setPhone(dto.getPhone());
            entity.setUseYn(dto.getUseYn());
            entity.setRole(role);

        userRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

         return resultMap;
    }

    @Transactional
    public Map<String, Object> useYnUpdate(String userId) throws Exception {
         Map<String, Object> resultMap = new HashMap<>();

        UserEntity entity = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        entity.setDelYn("Y");
        userRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

         return resultMap;
    }
}
