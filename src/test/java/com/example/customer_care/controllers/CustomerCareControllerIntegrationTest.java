package com.example.customer_care.controllers;

import com.example.customer_care.CustomSystemProfileValueSource;
import com.example.customer_care.controller.CustomerCareController;
import com.example.customer_care.entity.CustomerComplaint;
import com.example.customer_care.exceptions.NewResourceNotAllowedInPutException;
import com.example.customer_care.repo.CustomerCareRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import java.util.UUID;


@ProfileValueSourceConfiguration(value = CustomSystemProfileValueSource.class)
@RunWith(SpringRunner.class)
@IfProfileValue(name= "spring.profiles.active" , value = "embedded")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerCareControllerIntegrationTest {



    @Autowired
    private CustomerCareController controller;



    @Test
    public void checkSave(){
        //Arrange
        String complaintId =  UUID.randomUUID().toString();
        CustomerComplaint complaint = getCustomerComplaint(complaintId);

        // Act
        ResponseEntity returnedComplaint = controller.createComplaint(complaint);

        // Assert


        Assert.assertEquals(201, returnedComplaint.getStatusCodeValue());



    }

    private CustomerComplaint getCustomerComplaint(String complaintId) {
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setId(complaintId);
        complaint.setFirstName("Pramod");
        complaint.setLastName("Nikam");
        complaint.setAgentId(1);
        complaint.setComplaintMessage("TDD is Top down or Bottom Up?");
        return complaint;
    }



    @Test(expected = NewResourceNotAllowedInPutException.class)
    public void checkUpdate() throws NewResourceNotAllowedInPutException{
        //Arrange
        String complaintId =  UUID.randomUUID().toString();
        CustomerComplaint complaint = getCustomerComplaint(complaintId);


        // Act

        ResponseEntity responseEntity = controller.updateComplaint(complaint);

        // Assert


        Assert.assertEquals(201, responseEntity.getStatusCodeValue());




    }





}
