package com.practice.api_quota_app.service;

import com.practice.api_quota_app.entity.Pack;
import com.practice.api_quota_app.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackService {


    private final PackRepository packRepository;
    @Autowired
    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public Pack savePack(Pack pack) {

        return packRepository.save(pack);
    }

    public List<Pack> getAllPacks() {

        return packRepository.findAll();
    }
    public Pack patchPack(Long id, Pack updatedPack) {

        Optional<Pack> optionalPack = packRepository.findById(id);

        if(optionalPack.isPresent()) {

            Pack existingPack = optionalPack.get();

            if(updatedPack.getPackName() != null) {
                existingPack.setPackName(updatedPack.getPackName());
            }

            if(updatedPack.getPrice() != null) {
                existingPack.setPrice(updatedPack.getPrice());
            }

            if(updatedPack.getApiLimit() != null) {
                existingPack.setApiLimit(updatedPack.getApiLimit());
            }

            return packRepository.save(existingPack);
        }

        return null;
    }
    // PUT -> Full Update
    public Pack updatePack(Long id, Pack updatedPack) {

//        Optional<Pack> optionalPack = packRepository.findById(id);
//
//        if(optionalPack.isPresent()) {
//
//            Pack existingPack = optionalPack.get();
//
//            existingPack.setPackName(updatedPack.getPackName());
//            existingPack.setPrice(updatedPack.getPrice());
//            existingPack.setApiLimit(updatedPack.getApiLimit());
//
//            return packRepository.save(existingPack);
//        }
        Pack existingPack = packRepository
                .findById(id)
                .orElseThrow();

        existingPack.setPackName(
                updatedPack.getPackName()
        );

        existingPack.setPrice(
                updatedPack.getPrice()
        );

        existingPack.setApiLimit(
                updatedPack.getApiLimit()
        );

        return packRepository.save(existingPack);

//        return null;
    }


    public void deletePack(Long id) {

        packRepository.deleteById(id);
    }
    public Pack fetchPack(Long id){
        return packRepository.findById(id)
                .orElseThrow();
    }

}
