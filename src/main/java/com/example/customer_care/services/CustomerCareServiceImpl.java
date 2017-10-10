package com.example.customer_care.services;

import com.example.customer_care.entity.CustomerComplaint;
import com.example.customer_care.exceptions.NewResourceNotAllowedInPutException;
import com.example.customer_care.repo.CustomerCareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerCareServiceImpl implements CustomerCareService {

    private CustomerCareRepository customerCareRepository;

    public CustomerCareServiceImpl(CustomerCareRepository customerCareRepository) {
        this.customerCareRepository = customerCareRepository;
    }

    @Override
    public CustomerComplaint create(CustomerComplaint complaint) {
        complaint.setId(UUID.randomUUID().toString());
        return customerCareRepository.save(complaint);
    }

    @Override
    public List<CustomerComplaint> getAll() {
        return customerCareRepository.findAll();
    }

    @Override
    public CustomerComplaint getById(String complaintId) {
        return customerCareRepository.findOne(complaintId);
    }

    @Override
    public Boolean delete(String complaintId) {
        CustomerComplaint complaint = customerCareRepository.findOne(complaintId);

        if (complaint != null) {
            customerCareRepository.delete(complaintId);
            CustomerComplaint recheckComplaint = customerCareRepository.findOne(complaintId);
            return (recheckComplaint != null ? false : true);
        } else {
            return false;
        }
    }

    @Override
    public CustomerComplaint updateWhole(CustomerComplaint complaint) throws NewResourceNotAllowedInPutException {
        CustomerComplaint existingComplaint = customerCareRepository.findOne(complaint.getId());
        if (existingComplaint == null) {
            throw new NewResourceNotAllowedInPutException();
        } else {
            complaint = customerCareRepository.save(complaint);
        }

        return complaint;

    }
}
