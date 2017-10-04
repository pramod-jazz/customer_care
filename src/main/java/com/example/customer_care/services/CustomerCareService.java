package com.example.customer_care.services;

import com.example.customer_care.entity.CustomerComplaint;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerCareService {
    CustomerComplaint create(CustomerComplaint complaint);

    List<CustomerComplaint> getAll();

    CustomerComplaint getById(String complaintId);
}
