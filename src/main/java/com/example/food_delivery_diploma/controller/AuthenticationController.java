package com.example.food_delivery_diploma.controller;


import com.example.food_delivery_diploma.dto.jwt.JwtRespDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAllArgsDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAuthDTO;
import com.example.food_delivery_diploma.entity.User;
import com.example.food_delivery_diploma.security.jwt.JwtUtils;
import com.example.food_delivery_diploma.security.service.UserDetailsImpl;
import com.example.food_delivery_diploma.service.UserService;
import com.example.food_delivery_diploma.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/signIn")
    public ResponseEntity<JwtRespDTO> authenticateUser(@Valid @RequestBody UserAuthDTO userAuthDTO, HttpSession httpSession){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDTO.getUsername(), userAuthDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userService.getUserByUsername(userAuthDTO.getUsername());
        httpSession.setAttribute("user", user);

        String token = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(JwtRespDTO.builder()
                .userId(user.getId())
                .username(userDetails.getUsername())
                .token(token)
                .expiration("86400000")
                .build());
    }

    @PostMapping("signUp")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserAllArgsDTO userAllArgsDTO){
        if (userService.addUser(ConverterOfDTO.getAllArgsUserDTO(userAllArgsDTO))){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }


}
