package com.resellerapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ConditionEnum name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "condition")
    private List<Offer> offers;

    public Condition() {
        this.offers = new ArrayList<Offer>();
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Condition(ConditionEnum condition, String description) {
        this();

        this.name = condition;
        this.description = description;
    }

    public ConditionEnum getName() {
        return name;
    }

    public void setName(ConditionEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
