package com.example.customer_care.controller;

import com.example.customer_care.entity.CustomerComplaint;
import com.example.customer_care.exceptions.NewResourceNotAllowedInPutException;
import com.example.customer_care.services.CustomerCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerCareController {

    @Autowired
    private CustomerCareService customerCareService;


    public CustomerCareController(CustomerCareService customerCareService) {
        this.customerCareService = customerCareService;
    }

    @PostMapping("complaints")
    public ResponseEntity createComplaint(@Valid @RequestBody CustomerComplaint complaint) {
        customerCareService.create(complaint);
        return new ResponseEntity(complaint, HttpStatus.CREATED);
    }


    @GetMapping("complaints")
    public ResponseEntity getAllComplaints() {
        List<CustomerComplaint> complaints = customerCareService.getAll();
        return new ResponseEntity(complaints, HttpStatus.OK);
    }

    @GetMapping("complaints/{complaintId}")
    public ResponseEntity getAllComplaints(@PathVariable String complaintId) {
        CustomerComplaint complaint = customerCareService.getById(complaintId);
        if (complaint != null) {
            return new ResponseEntity(complaint, HttpStatus.OK);
        } else {
            return new ResponseEntity("No record found.", HttpStatus.NO_CONTENT);
        }

    }

    @PutMapping("complaints/{complaintId}")
    public ResponseEntity updateComplaint(@Valid @RequestBody CustomerComplaint complaint) throws NewResourceNotAllowedInPutException {

        complaint = customerCareService.updateWhole(complaint);
        return new ResponseEntity(complaint, HttpStatus.CREATED);

    }


    @DeleteMapping("complaints/{complaintId}")
    public ResponseEntity removeComplaint(@PathVariable String complaintId) {
        Boolean isSuccess = customerCareService.delete(complaintId);
        if (isSuccess) {
            return new ResponseEntity("Record removed successfully.", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity("No record found to remove.", HttpStatus.NOT_FOUND);
        }

    }

}
