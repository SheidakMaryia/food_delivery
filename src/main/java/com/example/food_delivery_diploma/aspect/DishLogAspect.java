package com.example.food_delivery_diploma.aspect;

import com.example.food_delivery_diploma.dto.dishDTO.DishAllArgsDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdNameCategoryPriceDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdPriceDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestNameCuisineAddressDTO;
import com.example.food_delivery_diploma.entity.Dish;
import com.example.food_delivery_diploma.entity.Restaurant;
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
public class DishLogAspect {
    private final Logger logger = LoggerFactory.getLogger(DishLogAspect.class.getSimpleName());


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.DishController.addDish(..)) && args(dishAllArgsDTO, ..))")
    public void addDish(DishAllArgsDTO dishAllArgsDTO){}

    @AfterReturning(value = "addDish(dishAllArgsDTO)", argNames = "dishAllArgsDTO, returningValue", returning = "returningValue")
    public void logOfAddDish(DishAllArgsDTO dishAllArgsDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.CREATED)){
            logger.info("New dish - {} added.", dishAllArgsDTO.getName());
        }else{
            logger.info("Dish - {} added unsuccessfully", dishAllArgsDTO.getName());
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.DishController.getDishById(..)) && args(id, ..))")
    public void getDishById(long id){}

    @AfterReturning(value = "getDishById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetDishById(long id, ResponseEntity<Dish> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Dish with id = {} found", id);
        }else{
            logger.info("Dish with id = {} not found", id);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.DishController.getDishesByCategory(..)) && args(category, ..))")
    public void getDishesByCategory(String category){}

    @AfterReturning(value = "getDishesByCategory(category)", argNames = "category, returningValue", returning = "returningValue")
    public void logOfGetDishesByCategory(String category, ResponseEntity<List<Dish>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Dishes of category {} found", category);
        }else{
            logger.info("Dishes of category {} not found", category);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.DishController.getDishesByLimitOfPrice(..)) && args(maxPrice, ..))")
    public void getDishesByLimitOfPrice(Double maxPrice){}

    @AfterReturning(value = "getDishesByLimitOfPrice(maxPrice)", argNames = "maxPrice, returningValue", returning = "returningValue")
    public void logOfGetDishesByLimitOfPrice(Double maxPrice, ResponseEntity<List<Dish>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Dishes with price less than {} found", maxPrice);
        }else{
            logger.info("Dishes with price less than {} not found", maxPrice);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.DishController.deleteDishById(..)) && args(id, ..))")
    public void deleteDishById(long id){}

    @AfterReturning(value = "deleteDishById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfDeleteDishById(long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Dish with id = {} successfully deleted", id);
        }else{
            logger.info("Dish with id = {} deletion failed!", id);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.DishController.updDish(..)) && args(dishDTO, id, ..))")
    public void updDish(DishIdNameCategoryPriceDTO dishDTO, long id){}

    @AfterReturning(value = "updDish(dishDTO, id)", argNames = "dishDTO, id, returningValue", returning = "returningValue")
    public void logOfUpdDish(DishIdNameCategoryPriceDTO dishDTO, long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Dish with id = {} successfully updated", id);
        }else{
            logger.info("The update of dish with id = - {} failed!", id);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.DishController.changePrice(..)) && args(dishIdPriceDTO, ..))")
    public void changePrice(DishIdPriceDTO dishIdPriceDTO){}

    @AfterReturning(value = "changePrice(dishIdPriceDTO)", argNames = "dishIdPriceDTO, returningValue", returning = "returningValue")
    public void logOfChangePrice(DishIdPriceDTO dishIdPriceDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Price of dish with id = {} successfully changed", dishIdPriceDTO.getId());
        }else{
            logger.info("Change price of dish with id = {} failed!", dishIdPriceDTO.getId());
        }
    }
}
