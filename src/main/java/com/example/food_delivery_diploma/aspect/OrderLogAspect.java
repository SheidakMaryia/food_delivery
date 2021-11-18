package com.example.food_delivery_diploma.aspect;

import com.example.food_delivery_diploma.dto.orderDTO.OrderDishIdAndUserIdDTO;
import com.example.food_delivery_diploma.entity.Order;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Aspect
@Component
public class OrderLogAspect {
    private final Logger logger = LoggerFactory.getLogger(OrderLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.OrderController.createOrder(..)) && args(orderDTO, ..))")
    public void createOrder(OrderDishIdAndUserIdDTO orderDTO){}

    @AfterReturning(value = "createOrder(orderDTO)", argNames = "orderDTO, returningValue", returning = "returningValue")
    public void logOfCreateOrder(OrderDishIdAndUserIdDTO orderDTO, ResponseEntity<Order> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.CREATED)){
            logger.info("Order of User id = {} created.", orderDTO.getUserId());
        }else{
            logger.info("Order of User id = {} created unsuccessfully", orderDTO.getUserId());
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.OrderController.getOrderByUserId(..)) && args(id, ..))")
    public void getOrderByUserId(long id){}

    @AfterReturning(value = "getOrderByUserId(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetOrderByUserId(long id, ResponseEntity<List<Order>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Restaurant with id = {} found", id);
        }else{
            logger.info("Restaurant with id = {} not found", id);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.OrderController.deleteOrderById(..)) && args(id, ..))")
    public void deleteOrderById(long id){}

    @AfterReturning(value = "deleteOrderById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfDeleteOrderById(long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Order with id = {} successfully deleted", id);
        }else{
            logger.info("Order with id = {} deletion failed!", id);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.OrderController.getArrivalTimeByUserId(..)) && args(id, ..))")
    public void getArrivalTimeByUserId(long id){}

    @AfterReturning(value = "getArrivalTimeByUserId(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetArrivalTimeByUserId(long id, ResponseEntity<LocalTime> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Arrival time of user's(id = {}) order found", id);
        }else{
            logger.info("Arrival time of user's(id = {}) order not found", id);
        }
    }
}
