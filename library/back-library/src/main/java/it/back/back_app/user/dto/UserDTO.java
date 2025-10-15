package it.back.back_app.user.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.back.back_app.security.entity.UserEntity;
import it.back.back_app.security.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDTO {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {
        private String userId;
        private String name;
        private String email;
        private String role;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime updateDate;

        public static Response of(UserEntity entity) {
            return Response.builder()
                    .userId(entity.getUserId())
                    .name(entity.getName())
                    .email(entity.getEmail())
                    .role(entity.getRole().name())
                    .build();
        }
        public LocalDateTime getModifiedDate() {
            return this.updateDate == null ? this.createDate : this.updateDate;
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Detail {
        private String userId;
        private String name;
        private String password;
        private String email;
        private String phone;
        private String address;
        private String addressDetail;
        private int cache;
        private String role;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime createDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime updateDate;

        public static Detail of(UserEntity entity) {
            return Detail.builder()
                    .userId(entity.getUserId())
                    .name(entity.getName())
                    .password(entity.getPassword())
                    .email(entity.getEmail())
                    .phone(entity.getPhone())
                    .address(entity.getAddress())
                    .addressDetail(entity.getAddressDetail())
                    .cache(entity.getCache())
                    .role(entity.getRole().name())
                    .createDate(entity.getCreateDate())
                    .updateDate(entity.getUpdateDate())
                    .build();
        }

        public static UserEntity to(Detail detail) {
            return UserEntity.builder()
                .userId(detail.getUserId())
                .name(detail.getName())
                .password(detail.getPassword())
                .email(detail.getEmail())
                .phone(detail.getPhone())
                .address(detail.getAddress())
                .addressDetail(detail.getAddressDetail())
                .cache(detail.getCache())
                .role(detail.getRole() == null ? UserRole.USER : UserRole.valueOf(detail.getRole()))
                .build();
        }

        public LocalDateTime getModifiedDate() {
            return this.updateDate == null ? this.createDate : this.updateDate;
        }
    }

    @Data
    public static class Request {
        @NotBlank(message="아이디는 필수 항목입니다.")
        private String userId;
        @NotBlank(message="이름은 필수 항목입니다.")
        private String name;
        @NotBlank(message="이메일은 필수 항목입니다.")
        private String email;
        @NotBlank(message="휴대폰번호는 필수 항목입니다.")
        private String phone;
    }
}
