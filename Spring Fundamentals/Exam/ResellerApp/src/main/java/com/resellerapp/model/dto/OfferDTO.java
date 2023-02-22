package com.resellerapp.model.dto;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.User;

import java.math.BigDecimal;

public class OfferDTO {

    private Long id;

    private String description;

    private BigDecimal price;

    private Condition condition;

    private User user;

    public OfferDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
