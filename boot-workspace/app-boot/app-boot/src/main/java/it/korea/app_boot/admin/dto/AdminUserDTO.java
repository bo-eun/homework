package it.korea.app_boot.admin.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import it.korea.app_boot.user.entity.UserEntity;
import it.korea.app_boot.user.entity.UserRoleEntity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminUserDTO {
    @Id
    private String userId;
    private String passwd;
    private String userName;
    private String birth;
    private String gender;
    private String phone;
    private String email;
    private String addr;
    private String addrDetail;
    private String useYn;
    private String delYn;
    private String userRole;
    private String roleName;
    @JsonFormat(shape=Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(shape=Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    public static AdminUserDTO of(UserEntity entity) {
        return AdminUserDTO
                .builder()
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .birth(entity.getBirth())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .addr(entity.getAddr())
                .addrDetail(entity.getAddrDetail())
                .userRole(entity.getRole().getRoleId())
                .roleName(entity.getRole().getRoleName())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .useYn(entity.getUseYn())
                .delYn(entity.getDelYn())
                .build();
    }

    public static AdminUserDTO of(AdminUserProjection entity) {
        return AdminUserDTO
                .builder()
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .birth(entity.getBirth())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .addr(entity.getAddr())
                .addrDetail(entity.getAddrDetail())
                .userRole(entity.getRoleId())
                .roleName(entity.getRoleName())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .useYn(entity.getUseYn())
                .delYn(entity.getDelYn())
                .build();
    }

    public static UserEntity to(AdminUserDTO dto) {
        UserEntity entity = new UserEntity();

        entity.setUserId(dto.getUserId());
        entity.setPasswd(dto.getPasswd());
        entity.setUserName(dto.getUserName());
        entity.setBirth(dto.getBirth());
        entity.setGender(dto.getGender());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setAddr(dto.getAddr());
        entity.setUseYn(dto.getUseYn());
        entity.setDelYn(dto.getDelYn() == null ? "N" : dto.getDelYn());
        entity.setAddrDetail(dto.getAddrDetail());

        return entity;

    }
}
