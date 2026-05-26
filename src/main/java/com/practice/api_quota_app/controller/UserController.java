package com.practice.api_quota_app.controller;

import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.repository.UserPackRepository;
import com.practice.api_quota_app.service.ApiUsageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    private final UserPackRepository userPackRepository;


    private final ApiUsageService apiUsageService;

    @Autowired
    public UserController(UserPackRepository userPackRepository, ApiUsageService apiUsageService) {
        this.userPackRepository = userPackRepository;
        this.apiUsageService = apiUsageService;
    }

    @GetMapping("/my-packs")
    public String myPacks(
            HttpSession session,
            Model model
    ) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        model.addAttribute(
                "userPacks",
                userPackRepository.findByUser(user)
        );

        model.addAttribute(
                "totalRemaining",
                apiUsageService
                        .getTotalRemainingCalls(user)
        );

        return "my-packs";
    }
    @GetMapping("/user-dashboard")
    public String userDashboard() {

        return "user-dashboard";
    }
}
