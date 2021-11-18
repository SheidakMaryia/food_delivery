package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Role;
import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {
    private final UserRepository userRepository;
    private final static List<User> users = new ArrayList<>();

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeAll
    void initializationOfUsers(){
        User user1 = User.builder()
                .id(1)
                .name("Masha")
                .username("masha@mail.ru")
                .password("Masha1")
                .telephone(Telephone.builder()
                        .id(1)
                        .number("123456789")
                        .build())
                .role(Role.USER)
                .address(Address.builder()
                        .city("Minsk")
                        .street("Skripnikova")
                        .numOfHouse(19)
                        .longitude(53.894267f)
                        .latitude(27.422978f)
                        .build())
                .build();
        User user2 = User.builder()
                .id(2)
                .name("Sasha")
                .username("sasha@mail.ru")
                .password("Sasha1")
                .telephone(Telephone.builder()
                        .id(2)
                        .number("987654321")
                        .build())
                .role(Role.USER)
                .address(Address.builder()
                        .city("Minsk")
                        .street("K.Chorny")
                        .numOfHouse(34)
                        .longitude(53.928747f)
                        .latitude(27.609927f)
                        .build())
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        users.add(user1);
        users.add(user2);
    }

    @Test
    void findByUsername() {
        assertEquals(users.get(0), userRepository.findByUsername(users.get(0).getUsername()).get());
    }

    @Test
    void getUserByUsername() {
        assertEquals(users.get(0), userRepository.getUserByUsername(users.get(0).getUsername()));
    }

    @Test
    void getByRole() {
        assertEquals(2, userRepository.getByRole(Role.USER).size());
    }
}