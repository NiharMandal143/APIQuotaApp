package com.practice.api_quota_app.controller;

import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.service.ApiUsageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ApiController {


    private  final ApiUsageService apiUsageService;
    @Autowired
    public ApiController(ApiUsageService apiUsageService) {
        this.apiUsageService = apiUsageService;
    }

    @GetMapping("/api/data")
    public String callApi(HttpSession session) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        if(user == null) {

            return "Please Login First";
        }

        Integer remaining =
                apiUsageService
                        .getTotalRemainingCalls(user);

        if(remaining <= 0) {

            return """
                    All the packs you bought
                    have been exhausted.

                    Kindly buy new packs
                    to resume your services.
                    """;
        }

        boolean consumed =
                apiUsageService.consumeApi(user);

        if(!consumed) {

            return """
                    API Consumption Failed
                    """;
        }

        Integer updatedRemaining =
                apiUsageService
                        .getTotalRemainingCalls(user);

        String[] generatedData = {

                "AI analytics generated.",

                "Weather insights generated.",

                "Customer report generated.",

                "Business prediction generated.",

                "Random AI response generated."
        };

        Random random = new Random();

        String randomData =
                generatedData[
                        random.nextInt(
                                generatedData.length
                        )
                        ];

        return """
                Hello %s (%s).

                API consumed successfully.

                Remaining API Calls: %d

                Generated Data:
                %s
                """
                .formatted(
                        user.getUsername(),
                        user.getRole().getRoleName(),
                        updatedRemaining,
                        randomData
                );
    }
}
