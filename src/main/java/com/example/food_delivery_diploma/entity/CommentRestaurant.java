package com.example.food_delivery_diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurantComments")
public class CommentRestaurant extends Comment{

    @Builder
    public CommentRestaurant(long id, String description, User user, Restaurant restaurant) {
        super(id, description, user);
        this.restaurant = restaurant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "CommentRestaurant{" +
                "restaurant=" + restaurant +
                '}';
    }
}
