package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.aggregators.UserAggregator;
import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Role;
import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
class UserServiceTest {

    private static final List<User> users = new ArrayList<>();
    private final UserService userService;

    @Autowired
    UserServiceTest(UserService userService) {
        this.userService = userService;
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
        userService.addUser(user1);
        userService.addUser(user2);
        users.add(user1);
        users.add(user2);
    }

    @ParameterizedTest
    @CsvSource({"1, Masha, masha@mail.ru, Masha1, 1, 123456789, Minsk, Skripnikova, 19, 53.894267f, 27.422978f",
            "2, Sasha, sasha@mail.ru, sasha1, 2, 987654321, Minsk, K.Chorny, 34, 53.928747f, 27.609927f"
    })
    @DisplayName("addUser")
    void addUser(@AggregateWith(UserAggregator.class) User user) {
        userService.addUser(user);
        assertEquals(2, userService.getAllUsers().size());//expected, actual
    }

    @ParameterizedTest
    @CsvSource({"1, Masha, masha@mail.ru, Masha1, 1, 123456789, Minsk, Skripnikova, 19, 53.894267f, 27.422978f"})
    @DisplayName("getUserByUsername")
    void getUserByUsername(@AggregateWith(UserAggregator.class) User user) {
        User userByUsername = userService.getUserByUsername(user.getUsername());
        assertEquals(user, userByUsername);
    }

    @ParameterizedTest
    @CsvSource({"1, Masha, masha@mail.ru, Masha1, 1, 123456789, Minsk, Skripnikova, 19, 53.894267f, 27.422978f"})
    @DisplayName("findUserByUsername")
    void findUserByUsername(@AggregateWith(UserAggregator.class) User user) {
        Optional<User> userByUsername = userService.findUserByUsername(user.getUsername());
        assertEquals(user, userByUsername.get());
    }

    @ParameterizedTest
    @CsvSource({"1, MashaNew, masha@mail.ru, Masha1, 1, 123456789, Minsk, Skripnikova, 19, 53.894267f, 27.422978f"})
    @DisplayName("updateUser")
    void updateUser(@AggregateWith(UserAggregator.class) User user) {
        userService.updateUser(users.get(0), user);
        assertEquals(users.get(0).getUsername(), user.getUsername());
    }

    @ParameterizedTest
    @CsvSource({"1, MashaNew, masha@mail.ru, Masha1, 1, 123456789, Minsk, Skripnikova, 19, 53.894267f, 27.422978f"})
    @DisplayName("updateUsername")
    void updateUsername(@AggregateWith(UserAggregator.class) User user) {
        userService.updateUsername(users.get(0), user.getUsername());
        assertEquals(users.get(0).getUsername(), user.getUsername());
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(1, users.get(0));
        assertEquals(1, userService.getAllUsers().size());
    }

    @Test
    void getAllUsers() {
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void getAllByRole() {
        String role = "user";
        assertEquals(2, userService.getAllByRole(role).size());
    }

    @Test
    void getUserById() {
        assertEquals(users.get(0), userService.getUserById(users.get(0).getId()).get());
    }

    @ParameterizedTest
    @CsvSource({"1, Masha, masha@mail.ru, Masha1, 1, 123456789newTel, Minsk, Skripnikova, 19, 53.894267f, 27.422978f"})
    @DisplayName("updateTelephone")
    void updateTelephone(@AggregateWith(UserAggregator.class) User user) {
        userService.updateTelephone(user.getTelephone(), users.get(0));
        assertEquals(user, users.get(0));
    }

    @ParameterizedTest
    @CsvSource({"1, Masha, masha@mail.ru, Masha1, 1, 123456789, MinskNewAddress, Skripnikova, 19, 53.894267f, 27.422978f"})
    @DisplayName("updateAddress")
    void updateAddress(@AggregateWith(UserAggregator.class) User user) {
        userService.updateAddress(user.getAddress(), users.get(0));
        assertEquals(user, users.get(0));
    }

    @Test
    void getAddressByUserId() {
        Optional<Address> addressByUserId = userService.getAddressByUserId(users.get(0).getId());
        assertEquals(users.get(0).getAddress(), addressByUserId.get());
    }
}