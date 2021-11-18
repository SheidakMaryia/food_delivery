package com.example.food_delivery_diploma.dto.jwt;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtRespDTO {
    private long userId;
    private String username;
    private String token;
    private String expiration; //время жизни токена
}
