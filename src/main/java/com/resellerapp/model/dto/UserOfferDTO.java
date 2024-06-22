package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;

public class UserOfferDTO {

    private long id;

    private String description;

    private Double price;

    private Condition condition;

    private User seller;

    public UserOfferDTO(Offer offer) {
        this.id = offer.getId();
        this.description = offer.getDescription();
        this.price = offer.getPrice();
        this.condition = offer.getCondition();
        this.seller = offer.getSeller();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
