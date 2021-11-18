package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Role;
import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.entity.User;
import com.example.food_delivery_diploma.repository.TelephoneRepository;
import com.example.food_delivery_diploma.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TelephoneRepository telephoneRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean addUser(User user){
        if (userRepository.existsById(user.getId())){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public User getUserByUsername(String username){//for security
        User byUsername = userRepository.getUserByUsername(username);
        return byUsername;
    }

    public Optional<User> findUserByUsername(String username){
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername;
    }

    public boolean updateUser(User oldUser, User updUser){
        if (userRepository.existsById(oldUser.getId())){
            oldUser.setName(updUser.getName());
            oldUser.setTelephone(updUser.getTelephone());
            oldUser.setAddress(updUser.getAddress());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }

    public boolean updateUsername(User user, String newUsername){
        if (user.getUsername().equals(newUsername)){
            return false;
        }else {
            user.setUsername(newUsername);
            userRepository.save(user);
            return true;
        }
    }

    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if (!oldPassword.equals(newPassword) && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUserById(long id, User user){
        if (userRepository.existsById(id) && user.getId() == id){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getAllByRole(String role){
        return userRepository.getByRole(Role.valueOf(role.toUpperCase(Locale.ENGLISH)));
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public boolean updateTelephone(Telephone telephone, User user){
        if (user.getTelephone().getNumber().equals(telephone.getNumber())){
            return false;
        }else {
            Telephone byId = user.getTelephone();
            byId.setNumber(telephone.getNumber());
            telephoneRepository.save(byId);
            return true;
        }
    }

    public boolean updateAddress(Address address, User user){
        if (user.getAddress().equals(address)){
            return false;
        }else{
            user.setAddress(address);
            userRepository.save(user);
            return true;
        }
    }

    public Optional<Address> getAddressByUserId(long userId){
        if (userRepository.existsById(userId)){
            User byId = userRepository.getById(userId);
            return Optional.of(byId.getAddress());
        }
        return Optional.empty();
    }
}
