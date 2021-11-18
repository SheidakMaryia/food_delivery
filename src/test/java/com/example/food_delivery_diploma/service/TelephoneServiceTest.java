package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.entity.Telephone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class TelephoneServiceTest {

    private static final List<Telephone> tels = new ArrayList<>();
    private final TelephoneService telephoneService;

    @Autowired
    TelephoneServiceTest(TelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }

    @BeforeAll
    void initializationOfTels(){
        Telephone tel1 = Telephone.builder()
                .id(1)
                .number("123456789")
                .build();
        Telephone tel2 = Telephone.builder()
                .id(2)
                .number("987654321")
                .build();
        telephoneService.saveTelephone(tel1);
        telephoneService.saveTelephone(tel2);
        tels.add(tel1);
        tels.add(tel2);
    }

    @Test
    void saveTelephone() {
        assertEquals(2, telephoneService.getAll().size());
    }

    @Test
    void deleteTelephoneById() {
        telephoneService.deleteTelephoneById(tels.get(0).getId());
        assertEquals(1, telephoneService.getAll().size());
    }

    @Test
    void getTelephoneById() {
        assertEquals(tels.get(0), telephoneService.getTelephoneById(tels.get(0).getId()).get());
    }
}