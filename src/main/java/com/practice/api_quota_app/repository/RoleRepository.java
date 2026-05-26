package com.practice.api_quota_app.repository;


import com.practice.api_quota_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}