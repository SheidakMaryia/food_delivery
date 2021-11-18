package com.example.food_delivery_diploma.aspect;

import com.example.food_delivery_diploma.dto.telephoneDTO.TelephoneDTO;
import com.example.food_delivery_diploma.entity.Telephone;
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
public class TelephoneLogAspect {
    private final Logger logger = LoggerFactory.getLogger(UserLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.TelephoneController.addTelephone(..)) && args(telephoneDTO, ..))")
    public void addTelephone(TelephoneDTO telephoneDTO){}

    @AfterReturning(value = "addTelephone(telephoneDTO)", argNames = "telephoneDTO, returningValue", returning = "returningValue")
    public void logOfAddTelephone(TelephoneDTO telephoneDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("New telephone - {} added.", telephoneDTO.getNumber());
        }else{
            logger.info("Telephone - {} added unsuccessfully", telephoneDTO.getNumber());
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.TelephoneController.deleteTelephoneById(..)) && args(id, ..))")
    public void deleteTelephoneById(long id){}

    @AfterReturning(value = "deleteTelephoneById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfDeleteTelephoneById(long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Telephone with id = {} successfully deleted", id);
        }else{
            logger.info("Telephone with id = {} deletion failed!", id);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.TelephoneController.getTelephoneById(..)) && args(id, ..))")
    public void getTelephoneById(long id){}

    @AfterReturning(value = "getTelephoneById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetTelephoneById(long id, ResponseEntity<Telephone> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Telephone with id = {} found", id);
        }else{
            logger.info("Telephone with id = {} not found", id);
        }
    }
}
