package com.example.customer_care.controller;

import com.example.customer_care.entity.CustomerComplaint;
import com.example.customer_care.repo.CustomerCareRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class CustomerCareController {


    CustomerCareRepository repository;

    public CustomerCareController(CustomerCareRepository repository) {
        this.repository = repository;
    }


    @PostMapping("customers")
    public ResponseEntity createComplaint(@Valid @RequestBody CustomerComplaint complaint){
           complaint.setId(UUID.randomUUID().toString());
            repository.save(complaint);
           return new ResponseEntity(complaint, HttpStatus.CREATED);
    }

}
