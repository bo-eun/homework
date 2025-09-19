package it.korea.app_boot.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.korea.app_boot.admin.dto.AdminUserDTO;
import it.korea.app_boot.admin.dto.AdminUserProjection;
import it.korea.app_boot.admin.dto.AdminUserSearchDTO;
import it.korea.app_boot.common.dto.PageVO;
import it.korea.app_boot.user.entity.UserEntity;
import it.korea.app_boot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final UserRepository userRepository;

    @Transactional
    public Map<String, Object> getUserList(Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = userRepository.findAll(pageable);

        List<AdminUserDTO> list = pageList.getContent().stream().map(AdminUserDTO::of).toList();

        PageVO pageVo = new PageVO();
        pageVo.setData(pageList.getNumber(), (int) pageList.getTotalElements());

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("content", list);
        resultMap.put("pageHTML", pageVo.pageHTML());
        resultMap.put("page", pageList.getNumber());

        return resultMap;
    }

    @Transactional
    public Map<String, Object> getUserList(Pageable pageable, AdminUserSearchDTO searchDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        
        Page<UserEntity> pageList = null;

        // null 포함 값이 없을 때
        if(StringUtils.isNotBlank(searchDTO.getSearchText())) {
            pageList = userRepository
                        .findByUserIdContainingOrUserNameContaining(searchDTO.getSearchText(), searchDTO.getSearchText(), pageable);
        } else {
            pageList = userRepository.findAll(pageable);
        }

        List<AdminUserDTO> list = pageList.getContent().stream().map(AdminUserDTO::of).toList();

        PageVO pageVo = new PageVO();
        pageVo.setData(pageList.getNumber(), (int) pageList.getTotalElements());

        resultMap.put("total", pageList.getTotalElements());
        resultMap.put("content", list);
        resultMap.put("pageHTML", pageVo.pageHTML());
        resultMap.put("page", pageList.getNumber());

        return resultMap;
    }

    @Transactional
    public AdminUserDTO getUser(String userId) throws Exception {
        AdminUserProjection user = userRepository.getUserById(userId).orElseThrow(() -> new RuntimeException("사용자 없음"));

        return AdminUserDTO.of(user);
    }
}

