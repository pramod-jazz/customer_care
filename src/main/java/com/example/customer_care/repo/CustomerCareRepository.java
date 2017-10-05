package com.example.customer_care.repo;


import com.example.customer_care.entity.CustomerComplaint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCareRepository extends MongoRepository<CustomerComplaint,String> {

}

