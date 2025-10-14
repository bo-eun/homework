package it.back.back_app.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.back.back_app.security.entity.UserEntity;

public interface AdminUserRepository extends JpaRepository<UserEntity, String> {

}
