//package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest
//@Transactional
//class DishServiceTest {

//    private static final List<Dish> dishes = new ArrayList<>();
//    private final DishService dishService;

//    @Autowired
//    DishServiceTest(DishService dishService) {
//        this.dishService = dishService;
//    }
//
//    @BeforeAll
//    void initializationOfDishes(){
//        Dish dish1 = Dish.builder()
//                .id(1)
//                .name("Ice-cream")
//                .category(Category.DESSERT)
//                .restaurant(Restaurant.builder()
//                        .id(1)
//                        .name("Union")
//                        .cuisine(Cuisine.WITHOUT)
//                        .address(Address.builder()
//                                .city("Minsk")
//                                .street("Y.Kupala")
//                                .numOfHouse(17)
//                                .longitude(53.894267f)
//                                .latitude(27.422978f)
//                                .build())
//                        .build())
//                .price(5.0)
//                .build();
//        Dish dish2 = Dish.builder()
//                .id(2)
//                .name("Cake")
//                .category(Category.DESSERT)
//                .restaurant(Restaurant.builder()
//                        .id(2)
//                        .name("Stolle")
//                        .cuisine(Cuisine.WITHOUT)
//                        .address(Address.builder()
//                                .city("Minsk")
//                                .street("Nezavisymosti")
//                                .numOfHouse(53)
//                                .longitude(53.917957f)
//                                .latitude(27.587361f)
//                                .build())
//                        .build())
//                .price(6.0)
//                .build();
//        dishes.add(dish1);
//        dishes.add(dish2);
//        dishService.addDish(dish1);
//        dishService.addDish(dish2);
//    }
//
//    @Test
//    void addDish() {
////        assertEquals(dishes.get(0), dishService.getDishById(dishes.get(0).getId()));
//    }
//
//    @Test
//    void updateDish() {
//    }
//
//    @Test
//    void deleteDish() {
////        dishService.deleteDish(dishes.get(0).getId());
////        assertEquals(1, );
//    }
//
//    @Test
//    void changePrice() {
//    }
//
//    @Test
//    void getDishById() {
//    }
//
//    @Test
//    void getDishesByCategory() {
//    }
//
//    @Test
//    void getDishesByLimitOfPrice() {
//    }
//}