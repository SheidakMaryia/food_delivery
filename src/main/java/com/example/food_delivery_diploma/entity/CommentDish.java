package com.example.food_delivery_diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dishСomments")
public class CommentDish extends Comment{

    @Builder
    public CommentDish(long id, String description, User user, Dish dish) {
        super(id, description, user);
        this.dish = dish;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Dish dish;

    @Override
    public String toString() {
        return "CommentDish{" +
                "dish=" + dish +
                '}';
    }
}
