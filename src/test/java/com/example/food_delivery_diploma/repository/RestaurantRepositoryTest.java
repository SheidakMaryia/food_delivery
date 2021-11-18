package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Cuisine;
import com.example.food_delivery_diploma.entity.Restaurant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestaurantRepositoryTest {
    private final RestaurantRepository restaurantRepository;
    private final static List<Restaurant> restaurants = new ArrayList<>();

    @Autowired
    RestaurantRepositoryTest(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @BeforeAll
    void initializationOfRestaurants(){
        Restaurant rest1 = Restaurant.builder()
                .id(1)
                .name("Union")
                .cuisine(Cuisine.WITHOUT)
                .address(Address.builder()
                        .city("Minsk")
                        .street("Y.Kupala")
                        .numOfHouse(17)
                        .longitude(53.894267f)
                        .latitude(27.422978f)
                        .build())
                .build();
        Restaurant rest2 = Restaurant.builder()
                .id(2)
                .name("Stolle")
                .cuisine(Cuisine.WITHOUT)
                .address(Address.builder()
                        .city("Minsk")
                        .street("Nezavisymosti")
                        .numOfHouse(53)
                        .longitude(53.917957f)
                        .latitude(27.587361f)
                        .build())
                .build();
        restaurantRepository.save(rest1);
        restaurantRepository.save(rest2);
        restaurants.add(rest1);
        restaurants.add(rest2);
    }

    @Test
    void getByCuisine() {
        assertEquals(2, restaurantRepository.getByCuisine(Cuisine.WITHOUT).size());
    }

    @Test
    void findByName() {
        assertEquals(restaurants.get(0), restaurantRepository.findByName(restaurants.get(0).getName()).get());
    }

    @Test
    void existsByNameAndAddress() {
        assertTrue(restaurantRepository.existsByNameAndAddress(restaurants.get(0).getName(),restaurants.get(0).getAddress()));
    }
}