package com.example.food_delivery_diploma.aspect;

import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestNameCuisineAddressDTO;
import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Restaurant;

import com.example.food_delivery_diploma.entity.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class RestaurantLogAspect {
    private final Logger logger = LoggerFactory.getLogger(RestaurantLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.addRestaurant(..)) && args(restAllArgsDTO, ..))")
    public void addRestaurant(RestAllArgsDTO restAllArgsDTO){}

    @AfterReturning(value = "addRestaurant(restAllArgsDTO)", argNames = "restAllArgsDTO, returningValue", returning = "returningValue")
    public void logOfAddRestaurant(RestAllArgsDTO restAllArgsDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("New restaurant - {} added.", restAllArgsDTO.getName());
        }else{
            logger.info("Restaurant - {} added unsuccessfully", restAllArgsDTO.getName());
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.deleteRestaurantById(..)) && args(id, ..))")
    public void deleteRestaurantById(long id){}

    @AfterReturning(value = "deleteRestaurantById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfDeleteRestaurantById(long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Restaurant with id = {} successfully deleted", id);
        }else{
            logger.info("Restaurant with id = {} deletion failed!", id);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.getRestaurantById(..)) && args(id, ..))")
    public void getRestaurantById(long id){}

    @AfterReturning(value = "getRestaurantById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetRestaurantById(long id, ResponseEntity<Restaurant> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Restaurant with id = {} found", id);
        }else{
            logger.info("Restaurant with id = {} not found", id);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.getRestaurantByName(..)) && args(name, ..))")
    public void getRestaurantByName(String name){}

    @AfterReturning(value = "getRestaurantByName(name)", argNames = "name, returningValue", returning = "returningValue")
    public void logOfGetRestaurantByName(String name, ResponseEntity<Restaurant> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Restaurant - {} found", name);
        }else{
            logger.info("Restaurant - {} not found", name);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.getRestaurantByCuisine(..)) && args(cuisine, ..))")
    public void getRestaurantByCuisine(String cuisine){}

    @AfterReturning(value = "getRestaurantByCuisine(cuisine)", argNames = "cuisine, returningValue", returning = "returningValue")
    public void logOfGetRestaurantByCuisine(String cuisine, ResponseEntity<List<Restaurant>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Restaurant of cuisine {} found", cuisine);
        }else{
            logger.info("Restaurant of cuisine {} not found", cuisine);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.updRestaurantNameCuisineAddress(..)) && args(restNameCuisineAddressDTO, ..))")
    public void updRestaurantNameCuisineAddress(RestNameCuisineAddressDTO restNameCuisineAddressDTO){}

    @AfterReturning(value = "updRestaurantNameCuisineAddress(restNameCuisineAddressDTO)", argNames = "restNameCuisineAddressDTO, returningValue", returning = "returningValue")
    public void logOfUpdRestaurantNameCuisineAddress(RestNameCuisineAddressDTO restNameCuisineAddressDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Restaurant with id = {} successfully updated", restNameCuisineAddressDTO.getId());
        }else{
            logger.info("The update of restaurant with id = - {} failed!", restNameCuisineAddressDTO.getId());
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.getAddressByRestaurantId(..)) && args(id, ..))")
    public void getAddressByRestaurantId(long id){}

    @AfterReturning(value = "getAddressByRestaurantId(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetAddressByRestaurantId(long id, ResponseEntity<Address> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Restaurant with id = {} found", id);
        }else{
            logger.info("Restaurant with id = {} not found", id);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.RestaurantController.getAllRestaurants(..))")
    public void getAllRestaurants(){}

    @AfterReturning(value = "getAllRestaurants()", argNames = "returningValue", returning = "returningValue")
    public void logOfGetAllRestaurants(ResponseEntity<List<Restaurant>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Request to get all restaurant.");
        }else{
            logger.info("Request to get all restaurant failed.");
        }
    }
}
