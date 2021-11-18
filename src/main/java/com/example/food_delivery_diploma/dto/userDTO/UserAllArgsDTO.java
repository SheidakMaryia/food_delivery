package com.example.food_delivery_diploma.dto.userDTO;

import com.example.food_delivery_diploma.dto.addressDTO.AddressDTO;
import com.example.food_delivery_diploma.dto.telephoneDTO.TelephoneDTO;
import com.example.food_delivery_diploma.entity.Role;
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
public class UserAllArgsDTO {

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String name;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.EMAIL, message = ConstraintMessageManager.EMAIL_ERROR)
    private String username;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    @Pattern(regexp = Patterns.PASSWORD, message = ConstraintMessageManager.PASSWORD_ERROR)
    private String password;

    private TelephoneDTO telephone;

    private AddressDTO address;

}
