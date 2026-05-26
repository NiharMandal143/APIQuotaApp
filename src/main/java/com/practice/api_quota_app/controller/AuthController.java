package com.practice.api_quota_app.controller;

import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {

        User user = userService.login(username, password);

        if(user == null) {

            model.addAttribute(
                    "error",
                    "Invalid Credentials"
            );

            return "home";
        }

        session.setAttribute("loggedInUser", user);

        String role = user.getRole().getRoleName();

        if(role.equals("ADMIN")) {

            return "admin-dashboard";
        }

        return "user-dashboard";
    }
}
