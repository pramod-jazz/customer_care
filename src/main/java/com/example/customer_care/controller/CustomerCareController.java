package com.example.customer_care.controller;

import com.example.customer_care.entity.CustomerComplaint;
import com.example.customer_care.repo.CustomerComplaintRepository;
import com.example.customer_care.service.CustomerCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerCareController {

    CustomerComplaintRepository repository;

    //CustomerCareService customerCareService;


    public CustomerCareController(CustomerComplaintRepository repository) {
        this.repository = repository;
    }

    @PostMapping("customers")
    public ResponseEntity createComplaint(@Valid @RequestBody CustomerComplaint complaint){
       //    customerCareService.create(complaint);
           return new ResponseEntity(complaint, HttpStatus.CREATED);
    }

}
