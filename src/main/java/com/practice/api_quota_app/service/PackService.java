package com.practice.api_quota_app.service;

import com.practice.api_quota_app.entity.Pack;
import com.practice.api_quota_app.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    public Pack savePack(Pack pack) {

        return packRepository.save(pack);
    }

    public List<Pack> getAllPacks() {

        return packRepository.findAll();
    }
}
