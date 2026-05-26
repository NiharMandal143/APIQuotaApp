package com.practice.api_quota_app.service;

import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.entity.UserPack;
import com.practice.api_quota_app.repository.UserPackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiUsageService {

    @Autowired
    private UserPackRepository userPackRepository;

    public Integer getTotalRemainingCalls(User user) {

        List<UserPack> userPacks =
                userPackRepository.findByUser(user);

        int total = 0;

        for(UserPack userPack : userPacks) {

            if(userPack.getStatus().equals("ACTIVE")) {

                total += userPack.getRemainingCalls();
            }
        }

        return total;
    }

    public boolean consumeApi(User user) {

        List<UserPack> userPacks =
                userPackRepository.findByUser(user);

        for(UserPack userPack : userPacks) {

            if(
                    userPack.getStatus().equals("ACTIVE")
                            &&
                            userPack.getRemainingCalls() > 0
            ) {

                userPack.setRemainingCalls(
                        userPack.getRemainingCalls() - 1
                );

                userPack.setUsedCalls(
                        userPack.getUsedCalls() + 1
                );

                if(userPack.getRemainingCalls() == 0) {

                    userPack.setStatus("EXHAUSTED");
                }

                userPackRepository.save(userPack);

                return true;
            }
        }

        return false;
    }
}