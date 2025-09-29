package it.exam.backoffice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.exam.backoffice.security.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {

}
