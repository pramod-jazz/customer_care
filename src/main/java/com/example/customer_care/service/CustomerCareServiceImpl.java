package com.example.customer_care.service;

import com.example.customer_care.entity.CustomerComplaint;

public class CustomerCareServiceImpl implements  CustomerCareService {


    @Override
    public CustomerComplaint create(CustomerComplaint customerComplaint) {
        customerComplaint.setId(1);
        return customerComplaint;
    }
}
