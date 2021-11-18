package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.Category;
import com.example.food_delivery_diploma.entity.Dish;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DishRepository extends JpaRepository<Dish, Long> {
    boolean existsByNameAndCategory(String name, Category category);
    List<Dish> getAllByCategory(Category category);

    @Query(value = "FROM Dish WHERE price <=:maxPrice")
    List<Dish> getByPrice(double maxPrice);
}
