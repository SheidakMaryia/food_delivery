package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.entity.User;
import com.example.food_delivery_diploma.repository.TelephoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelephoneService {

    private final TelephoneRepository telephoneRepository;

    public boolean saveTelephone(Telephone telephone){
        if (telephoneRepository.existsById(telephone.getId())){
            return false;
        }
        telephoneRepository.save(telephone);
        return true;
    }

    public boolean deleteTelephoneById(long id){
        if (telephoneRepository.existsById(id)){
            telephoneRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Telephone> getTelephoneById(long id) {
        return telephoneRepository.findById(id);
    }

    public List<Telephone> getAll(){
        return telephoneRepository.findAll();
    }
}
