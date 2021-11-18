package com.example.food_delivery_diploma.controller;

import com.example.food_delivery_diploma.dto.addressDTO.AddressDTO;
import com.example.food_delivery_diploma.dto.telephoneDTO.TelephoneDTO;
import com.example.food_delivery_diploma.dto.userDTO.ChangePasswordDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserNameAddressTelDTO;
import com.example.food_delivery_diploma.dto.userDTO.UsernameDTO;
import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.entity.User;
import com.example.food_delivery_diploma.service.UserService;
import com.example.food_delivery_diploma.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        Optional<User> userByUsername = userService.findUserByUsername(username);
        if (userByUsername.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userByUsername.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/updUser")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserNameAddressTelDTO userNameAddressTelDTO, HttpSession httpSession){
        User allArgsUserDTO = ConverterOfDTO.getUserNameAddressTelDTO(userNameAddressTelDTO);
        User user = (User) httpSession.getAttribute("user");
        if (userService.updateUser(user, allArgsUserDTO)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/updUsername")
    public ResponseEntity<String> updateUsername(@Valid @RequestBody UsernameDTO usernameDTO, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (userService.updateUsername(user, usernameDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/updPassword")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (userService.changePassword(user, changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword())){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (userService.deleteUserById(userId, user)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(HttpSession httpSession){
        List<User> allUsers = userService.getAllUsers();
        if (allUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping("/getAllUsersByRole/{role}")
    public ResponseEntity<List<User>> getAllUsersByRole( @PathVariable String role, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        List<User> allByRole = userService.getAllByRole(role);
        if (allByRole.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(allByRole);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById( @PathVariable long id){
        Optional<User> userById = userService.getUserById(id);
        if (userById.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userById.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getTelephoneByUserId/{id}")
    public ResponseEntity<Telephone> getTelephoneByUserId(@PathVariable long id){
        Optional<User> userById = userService.getUserById(id);
        if (userById.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(userById.get().getTelephone());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/updTelephone")
    public ResponseEntity<String> updTelephone(@Valid @RequestBody TelephoneDTO telephoneDTO, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Telephone telephoneDTO1 = ConverterOfDTO.getTelephoneDTO(telephoneDTO);
        if (userService.updateTelephone(telephoneDTO1, user)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/updAddress")
    public ResponseEntity<String> updAddress(@Valid @RequestBody AddressDTO addressDTO, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Address addressDTO1 = ConverterOfDTO.getAddressDTO(addressDTO);
        if (userService.updateAddress(addressDTO1, user)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getAddressByUserId/{id}")
    public ResponseEntity<Address> getAddressByUserId(@PathVariable long id){
        Optional<Address> addressByUserId = userService.getAddressByUserId(id);
        if (addressByUserId.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(addressByUserId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
