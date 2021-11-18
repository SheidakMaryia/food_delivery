package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
//    boolean existsByNumber(String number);
//
//    @Query(value = "SELECT t.id, t.number FROM Users WHERE User.ID = ?1 LEFT JOIN Telephones as t WHERE User.TELEPHONE_ID = t.id", nativeQuery = true)
//    Optional<Telephone> findByUserId(long id);

}
