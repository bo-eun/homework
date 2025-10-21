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
import it.back.back_app.admin.dto.PolicyDTO;
import it.back.back_app.admin.entity.PolicyEntity;
import it.back.back_app.admin.repository.AdminRepository;
import it.back.back_app.admin.repository.AdminUserSearchSpecification;
import it.back.back_app.admin.repository.PolicyRepository;
import it.back.back_app.security.entity.UserEntity;
import it.back.back_app.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder  passwordEncoder;
    private final PolicyRepository policyRepository;

    /* 전체 유저 리스트 */
    @Transactional
    public Map<String, Object> getUserList(Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = adminRepository.findAll(pageable);

        List<UserDTO.Response> list = pageList.getContent().stream().map(UserDTO.Response::of).toList();

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("content", list);
        resultMap.put("page", pageList.getNumber());

        return resultMap;
    }

    /* 검색한 유저 리스트 */
    @Transactional
    public Map<String, Object> getUserList(AdminUserSearchDTO searchDTO, Pageable pageable) {
       Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = null;

        AdminUserSearchSpecification adminUserSearchSpecification = new AdminUserSearchSpecification(searchDTO);
   
        pageList = adminRepository.findAll(adminUserSearchSpecification, pageable);

        List<UserDTO.Response> list = pageList.getContent().stream().map(UserDTO.Response::of).toList();

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("content", list);
        resultMap.put("page", pageList.getNumber());

        return resultMap;
    }

    @Transactional
    public UserDTO.Detail getUser(String userId) throws Exception {
        UserEntity user = adminRepository.getUserByUserId(userId).orElseThrow(() -> new RuntimeException("사용자 없음"));

        return UserDTO.Detail.of(user);
    }

    /* 유저 정보 수정 */
    @Transactional
    public Map<String, Object> updateUser(UserDTO.Detail dto) throws Exception {
         Map<String, Object> resultMap = new HashMap<>();
         
        UserEntity entity = adminRepository.findById(dto.getUserId())
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

        adminRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");

         return resultMap;
    }


    /* 교환/환불/반품 정책 설정 */
    @Transactional
    public Map<String, Object> getPolicy() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PolicyEntity> list = policyRepository.findAll();

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");
        resultMap.put("content", list);
        return resultMap;
    }

    /* 교환/환불/반품 정책 설정 */
    @Transactional
    public Map<String, Object> createPolicy(PolicyDTO request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        PolicyEntity entity = new PolicyEntity();

        entity = PolicyDTO.to(request);
        
        policyRepository.save(entity);

        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");
        resultMap.put("content", entity);
        return resultMap;
    }

    @Transactional
    public Map<String, Object> deletePolicy(int policyId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        policyRepository.deleteById(policyId);
        resultMap.put("resultCode", 200);
        resultMap.put("resultMsg", "OK");
        return resultMap;
    }

}
