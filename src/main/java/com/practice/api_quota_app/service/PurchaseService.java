package com.practice.api_quota_app.service;

import com.practice.api_quota_app.entity.DummyCard;
import com.practice.api_quota_app.entity.Pack;
import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.entity.UserPack;
import com.practice.api_quota_app.repository.DummyCardRepository;
import com.practice.api_quota_app.repository.UserPackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private DummyCardRepository dummyCardRepository;

    @Autowired
    private UserPackRepository userPackRepository;

    public boolean validateCard(String cardNumber) {

        Optional<DummyCard> card =
                dummyCardRepository.findByCardNumber(
                        cardNumber
                );

        return card.isPresent();
    }

    public void buyPack(User user, Pack pack) {

        UserPack userPack = new UserPack();

        userPack.setUser(user);

        userPack.setPack(pack);

        userPack.setUsedCalls(0);

        userPack.setRemainingCalls(
                pack.getApiLimit()
        );

        userPack.setStatus("ACTIVE");

        userPackRepository.save(userPack);
    }
}