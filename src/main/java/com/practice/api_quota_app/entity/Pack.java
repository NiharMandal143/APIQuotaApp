package com.practice.api_quota_app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "packs")
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String packName;

    private Double price;

    private Integer apiLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getApiLimit() {
        return apiLimit;
    }

    public void setApiLimit(Integer apiLimit) {
        this.apiLimit = apiLimit;
    }
}