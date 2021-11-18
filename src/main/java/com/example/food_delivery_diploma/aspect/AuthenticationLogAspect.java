package com.example.food_delivery_diploma.aspect;

import com.example.food_delivery_diploma.dto.jwt.JwtRespDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAllArgsDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAuthDTO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationLogAspect {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.AuthenticationController.registerUser(..)) && args(userAllArgsDTO, ..))")
    public void registerUser(UserAllArgsDTO userAllArgsDTO){}

    @AfterReturning(value = "registerUser(userAllArgsDTO)", argNames = "userAllArgsDTO, returningValue", returning = "returningValue")
    public void logOfRegisterUser(UserAllArgsDTO userAllArgsDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Register new user - {}.", userAllArgsDTO.getUsername());
        }else{
            logger.info("User - {} already exists", userAllArgsDTO.getUsername());
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.AuthenticationController.authenticateUser(..)) && args(userAuthDTO, ..))")
    public void authenticateUser(UserAuthDTO userAuthDTO){}

    @AfterReturning(value = "authenticateUser(userAuthDTO)", argNames = "userAuthDTO, returningValue", returning = "returningValue")
    public void logOfAuthenticateUser(UserAuthDTO userAuthDTO, ResponseEntity<JwtRespDTO> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User - {} logged in.", userAuthDTO.getUsername());
        }else{
            logger.info("User - {} has an authorization problem", userAuthDTO.getUsername());
        }

    }
}
