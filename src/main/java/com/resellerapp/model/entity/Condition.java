package com.resellerapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Column(nullable = false, unique = true)
    private ConditionEnum name;

    @Column(nullable = false)
    private String description;

    public Condition() {
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
