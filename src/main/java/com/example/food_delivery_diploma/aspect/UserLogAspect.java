package com.example.food_delivery_diploma.aspect;

import com.example.food_delivery_diploma.dto.addressDTO.AddressDTO;
import com.example.food_delivery_diploma.dto.telephoneDTO.TelephoneDTO;
import com.example.food_delivery_diploma.dto.userDTO.ChangePasswordDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserNameAddressTelDTO;
import com.example.food_delivery_diploma.dto.userDTO.UsernameDTO;
import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.entity.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class UserLogAspect {
    private final Logger logger = LoggerFactory.getLogger(UserLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.findByUsername(..)) && args(username, ..))")
    public void findByUsername(String username){}

    @AfterReturning(value = "findByUsername(username)", argNames = "username, returningValue", returning = "returningValue")
    public void logOfFindByUsername(String username, ResponseEntity<User> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User - {} found", username);
        }else{
            logger.info("User - {} not found", username);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.updateUser(..)) && args(userNameAddressTelDTO, ..))")
    public void updateUser(UserNameAddressTelDTO userNameAddressTelDTO){}

    @AfterReturning(value = "updateUser(userNameAddressTelDTO)", argNames = "userNameAddressTelDTO, returningValue", returning = "returningValue")
    public void logOfUpdateUser(UserNameAddressTelDTO userNameAddressTelDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User - {} successfully updated", userNameAddressTelDTO.getName());
        }else{
            logger.info("The update of User - {} failed!", userNameAddressTelDTO.getName());
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.updateUsername(..)) && args(usernameDTO, ..))")
    public void updateUsername(UsernameDTO usernameDTO){}

    @AfterReturning(value = "updateUsername(usernameDTO)", argNames = "usernameDTO, returningValue", returning = "returningValue")
    public void logOfUpdateUsername(UsernameDTO usernameDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Username of user - {} successfully updated", usernameDTO.getUsername());
        }else{
            logger.info("The update of User - {} failed!", usernameDTO.getUsername());
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.updatePassword(..)) && args(changePasswordDTO, ..))")
    public void updatePassword(ChangePasswordDTO changePasswordDTO){}

    @AfterReturning(value = "updatePassword(changePasswordDTO)", argNames = "changePasswordDTO, returningValue", returning = "returningValue")
    public void logOfUpdatePassword(ChangePasswordDTO changePasswordDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Password successfully updated");
        }else{
            logger.info("The update of password failed!");
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.deleteUser(..)) && args(userId, ..))")
    public void deleteUser(long userId){}

    @AfterReturning(value = "deleteUser(userId)", argNames = "userId, returningValue", returning = "returningValue")
    public void logOfDeleteUser(long userId, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User with id = {} successfully deleted", userId);
        }else{
            logger.info("User with id = {} deletion failed!", userId);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.getAllUsers(..))")
    public void getAllUsers(){}

    @AfterReturning(value = "getAllUsers()", argNames = "returningValue", returning = "returningValue")
    public void logOfGetAllUsers(ResponseEntity<List<User>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Request to get all users.");
        }else{
            logger.info("Request to get all users failed.");
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.getAllUsersByRole(..))")
    public void getAllUsersByRole(){}

    @AfterReturning(value = "getAllUsersByRole()", argNames = "returningValue", returning = "returningValue")
    public void logOfGetAllUsersByRole(ResponseEntity<List<User>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Request to get all users by role.");
        }else{
            logger.info("request to get all users by role failed.");
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.getUserById(..)) && args(id, ..))")
    public void getUserById(long id){}

    @AfterReturning(value = "getUserById(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetUserById(long id, ResponseEntity<User> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User with id = {} found", id);
        }else{
            logger.info("User with id = {} not found", id);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.getTelephoneByUserId(..)) && args(id, ..))")
    public void getTelephoneByUserId(long id){}

    @AfterReturning(value = "getTelephoneByUserId(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetTelephoneByUserId(long id, ResponseEntity<Telephone> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Telephone of user with id = {} found", id);
        }else{
            logger.info("Telephone of user with id = {} not found", id);
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.updTelephone(..)) && args(telephoneDTO, ..))")
    public void updTelephone(TelephoneDTO telephoneDTO){}

    @AfterReturning(value = "updTelephone(telephoneDTO)", argNames = "telephoneDTO, returningValue", returning = "returningValue")
    public void logOfUpdateTelephone(TelephoneDTO telephoneDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Telephone successfully updated");
        }else{
            logger.info("The update of telephone failed!");
        }
    }

    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.updAddress(..)) && args(addressDTO, ..))")
    public void updAddress(AddressDTO addressDTO){}

    @AfterReturning(value = "updAddress(addressDTO)", argNames = "addressDTO, returningValue", returning = "returningValue")
    public void logOfUpdAddress(AddressDTO addressDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Address successfully updated");
        }else{
            logger.info("The update of address failed!");
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.UserController.getAddressByUserId(..)) && args(id))")
    public void getAddressByUserId(long id){}

    @AfterReturning(value = "getAddressByUserId(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfGetAddressByUserId(long id, ResponseEntity<Address> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Address of user with id = {} found", id);
        }else{
            logger.info("Address of user with id = {} not found", id);
        }
    }
}
