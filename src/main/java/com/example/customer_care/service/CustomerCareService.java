package com.example.customer_care.service;

import com.example.customer_care.entity.CustomerComplaint;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public interface CustomerCareService {

    CustomerComplaint create(CustomerComplaint customerComplaint);
}
