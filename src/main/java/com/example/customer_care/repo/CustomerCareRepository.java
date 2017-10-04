package com.example.customer_care.repo;


import com.example.customer_care.entity.CustomerComplaint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCareRepository extends MongoRepository<CustomerComplaint,String> {

}

