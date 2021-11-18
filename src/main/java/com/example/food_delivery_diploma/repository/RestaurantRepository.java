package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> getByCuisine(Cuisine cuisine);
    Optional<Restaurant> findByName(String name);
    boolean existsByNameAndAddress(String name, Address address);
}
