package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.Role;
import com.example.food_delivery_diploma.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User getUserByUsername(String username);
    List<User> getByRole(Role role);
}
