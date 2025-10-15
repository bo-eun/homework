package it.back.back_app.security.entity;

import it.back.back_app.common.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name="users")
public class UserEntity extends BaseEntity{
    @Id
    private String userId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String addressDetail;
    private int cache;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

}
