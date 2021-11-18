package com.example.food_delivery_diploma.dto.userDTO;

import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import com.example.food_delivery_diploma.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDTO {

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.EMAIL, message = ConstraintMessageManager.EMAIL_ERROR)
    private String username;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintMessageManager.PASSWORD_ERROR)
    private String password;
}
