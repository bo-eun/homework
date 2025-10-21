package it.back.back_app.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.back.back_app.admin.entity.PolicyEntity;

public interface PolicyRepository extends JpaRepository<PolicyEntity, Integer> {

}
