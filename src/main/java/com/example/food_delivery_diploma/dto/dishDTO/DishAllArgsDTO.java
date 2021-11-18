package com.example.food_delivery_diploma.dto.dishDTO;

import com.example.food_delivery_diploma.dto.categoryDTO.CategoryDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestIdDTO;
import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishAllArgsDTO {

    //private long id;
    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String name;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String category;

    //private RestAllArgsDTO restaurant;
    private RestIdDTO restIdDTO;

    private double price;
}
