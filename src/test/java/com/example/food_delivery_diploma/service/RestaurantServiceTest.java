package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.aggregators.RestaurantAggregator;
import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Cuisine;
import com.example.food_delivery_diploma.entity.Restaurant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
class RestaurantServiceTest {

    private static final List<Restaurant> restaurants = new ArrayList<>();
    private final RestaurantService restaurantService;

    @Autowired
    RestaurantServiceTest(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
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
        restaurants.add(rest1);
        restaurants.add(rest2);
        restaurantService.addRestaurant(rest1);
        restaurantService.addRestaurant(rest2);
    }

    @Test
    void addRestaurant() {
        assertEquals(restaurants.size(), restaurantService.getAll().size());
    }

    @Test
    void deleteRestaurantById() {
        restaurantService.deleteRestaurantById(restaurants.get(0).getId());
        assertEquals(1, restaurantService.getAll().size());
    }

    @Test
    void getById() {
        assertEquals(restaurants.get(0), restaurantService.getById(restaurants.get(0).getId()).get());
    }

    @Test
    void getRestaurantByName() {
        assertEquals(restaurants.get(0), restaurantService.getRestaurantByName(restaurants.get(0).getName()).get());
    }

    @Test
    void getRestaurantByCuisine() {
        List<Restaurant> restaurantByCuisine = restaurantService.getRestaurantByCuisine(restaurants.get(0).getCuisine().name());
        assertEquals(2, restaurantByCuisine.size());
    }

    @ParameterizedTest
    @CsvSource({"1, UnionNew, Minsk, Y.KupalaNew, 17, 53.894267f, 27.422978f"})
    @DisplayName("updateNameCuisineAddress")
    void updateNameCuisineAddress(@AggregateWith(RestaurantAggregator.class) Restaurant restaurant) {
        restaurantService.updateNameCuisineAddress(restaurant);
        assertEquals(restaurant, restaurants.get(0));
    }

    @Test
    void getAddressesByRestaurantId() {
        assertEquals(restaurants.get(0).getAddress(), restaurantService.getAddressesByRestaurantId(restaurants.get(0).getId()).get());
    }
}