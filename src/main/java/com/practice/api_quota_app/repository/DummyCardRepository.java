package com.practice.api_quota_app.repository;

import com.practice.api_quota_app.entity.DummyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DummyCardRepository
        extends JpaRepository<DummyCard, Long> {

    Optional<DummyCard> findByCardNumber(
            String cardNumber
    );
}