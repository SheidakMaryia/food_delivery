package com.example.food_delivery_diploma.dto.orderDTO;

import com.example.food_delivery_diploma.dto.dishDTO.DishAllArgsDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAllArgsDTO;
import com.example.food_delivery_diploma.entity.Dish;
import com.example.food_delivery_diploma.entity.StatusOfOrder;
import com.example.food_delivery_diploma.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishUserDTO {

    private DishAllArgsDTO dish;

    private UserAllArgsDTO user;
}
