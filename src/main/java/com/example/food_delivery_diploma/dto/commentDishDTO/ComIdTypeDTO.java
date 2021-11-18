package com.example.food_delivery_diploma.dto.commentDishDTO;

import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestIdDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAllArgsDTO;
import com.example.food_delivery_diploma.entity.TypeOfComment;
import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComIdTypeDTO {
    long id;

    private TypeOfComment type;
}
