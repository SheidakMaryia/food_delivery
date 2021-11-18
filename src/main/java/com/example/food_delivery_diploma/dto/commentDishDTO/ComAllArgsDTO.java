package com.example.food_delivery_diploma.dto.commentDishDTO;

import com.example.food_delivery_diploma.dto.dishDTO.DishAllArgsDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAllArgsDTO;
import com.example.food_delivery_diploma.entity.TypeOfComment;
import com.example.food_delivery_diploma.entity.User;
import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComAllArgsDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long commId;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String description;

    private long user;

    private long id;//dishId or restaurantId

    private TypeOfComment type;
}
