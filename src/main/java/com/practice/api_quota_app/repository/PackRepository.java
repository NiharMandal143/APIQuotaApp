package com.practice.api_quota_app.repository;

import com.practice.api_quota_app.entity.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack, Long> {
}