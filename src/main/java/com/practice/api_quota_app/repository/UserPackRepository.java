package com.practice.api_quota_app.repository;

import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.entity.UserPack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPackRepository
        extends JpaRepository<UserPack, Long> {

    List<UserPack> findByUser(User user);
//    List<UserPack> findByUser(User user);
}