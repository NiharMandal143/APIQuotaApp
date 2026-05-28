package com.practice.api_quota_app.controller;

import com.practice.api_quota_app.entity.Pack;
import com.practice.api_quota_app.entity.User;
import com.practice.api_quota_app.repository.PackRepository;
import com.practice.api_quota_app.service.PackService;
import com.practice.api_quota_app.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class PackController {


    private final PackService packService;

    private final PackRepository packRepository;


    private  final PurchaseService purchaseService;
@Autowired
    public PackController(PackService packService, PackRepository packRepository, PurchaseService purchaseService) {
        this.packService = packService;
        this.packRepository = packRepository;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/create-pack")
    public String createPackPage() {

        return "create-pack";
    }

    @PostMapping("/save-pack")
    public String savePack(
            @RequestParam String packName,
            @RequestParam Double price,
            @RequestParam Integer apiLimit,
            Model model
    ) {

        Pack pack = new Pack();

        pack.setPackName(packName);
        pack.setPrice(price);
        pack.setApiLimit(apiLimit);

        packService.savePack(pack);

        model.addAttribute(
                "message",
                "Pack Created Successfully"
        );

        return "create-pack";
    }

    @GetMapping("/view-packs")
    public String viewPacks(Model model) {

        model.addAttribute(
                "packs",
                packService.getAllPacks()
        );

        return "view-packs";
    }
    @GetMapping("/buy-pack/{id}")
    public String buyPackPage(
            @PathVariable Long id,
            Model model
    ) {

        Pack pack = packRepository.findById(id).orElse(null);

        model.addAttribute("pack", pack);

        return "buy-pack";
    }

    @PostMapping("/purchase-pack")
    public String purchasePack(

            @RequestParam Long packId,
            @RequestParam String cardNumber,

            HttpSession session,

            Model model
    ) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        Pack pack =
                packRepository.findById(packId)
                        .orElse(null);

        boolean validCard =
                purchaseService.validateCard(
                        cardNumber
                );

        if(!validCard) {

            model.addAttribute(
                    "message",
                    "Payment Failed. Invalid Card."
            );

            model.addAttribute("pack", pack);

            return "buy-pack";
        }

        purchaseService.buyPack(user, pack);

        model.addAttribute(
                "message",
                "Pack Purchased Successfully"
        );

        return "purchase-success";
    }
    @PatchMapping("/{id}")
    public Pack patchPack(
            @PathVariable Long id,
            @RequestBody Pack updatedPack
    ) {

        return packService.patchPack(id, updatedPack);
    }
    // =========================
    // PUT API -> Full Update
    // =========================

    @PutMapping("/{id}")
    public Pack updatePack(
            @PathVariable Long id,
            @RequestBody Pack updatedPack
    ) {

        return packService.updatePack(id, updatedPack);
    }
}
