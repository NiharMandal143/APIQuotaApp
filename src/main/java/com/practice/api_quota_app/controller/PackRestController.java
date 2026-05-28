package com.practice.api_quota_app.controller;

import com.practice.api_quota_app.entity.Pack;
import com.practice.api_quota_app.service.PackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packs")
@CrossOrigin(origins = "http://localhost:5173")
public class PackRestController {

    @Autowired
    private PackService packService;

    // =========================
    // GET ALL PACKS
    // =========================

    @GetMapping
    public List<Pack> getAllPacks() {

        return packService.getAllPacks();
    }

    // =========================
    // CREATE PACK
    // =========================

    @PostMapping
    public Pack createPack(
            @RequestBody Pack pack
    ) {
      System.out.println(pack);
        return packService.savePack(pack);
    }


    // =========================
    // FULL UPDATE
    // =========================

    @PutMapping("/{id}")
    public Pack updatePack(

            @PathVariable Long id,

            @RequestBody Pack updatedPack
    ) {

        return packService.updatePack(
                id,
                updatedPack
        );
    }
    //    @PutMapping("/{id}")
//    public Pack updatePack(
//            @PathVariable Long id,
//            @RequestBody Pack updatedPack
//    ) {
//        return  packService.updatePack(id, updatedPack);
//
//    }

    // =========================
    // PARTIAL UPDATE
    // =========================

    @PatchMapping("/{id}")
    public Pack patchPack(

            @PathVariable Long id,

            @RequestBody Pack updatedPack
    ) {

        return packService.patchPack(
                id,
                updatedPack
        );
    }

    // =========================
    // DELETE PACK
    // =========================

    @DeleteMapping("/{id}")
    public String deletePack(
            @PathVariable Long id
    ) {

        packService.deletePack(id);

        return "Pack Deleted Successfully";
    }
    @GetMapping("/{id}")
    public Pack getPackById(
            @PathVariable Long id
    ) {

       return packService.fetchPack(id);
    }


}