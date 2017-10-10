package com.example.customer_care.controllers;

import com.example.customer_care.controller.CustomerCareController;
import com.example.customer_care.entity.CustomerComplaint;
import com.example.customer_care.exceptions.NewResourceNotAllowedInPutException;
import com.example.customer_care.services.CustomerCareService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerCareController.class)
public class CustomerCareControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerCareService service;

    private ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private CustomerCareController customerCareController;


    // Arrange
    @Before
    public void setup() {
        initMocks(this);
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    @Test
    public void testShouldCreateCustomerComplaint() throws Exception {

        //Accept
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setFirstName("Pramod");
        complaint.setLastName("Nikam");
        complaint.setAgentId(1);
        complaint.setComplaintMessage("TDD is Top down or Bottom Up?");

        when(this.service.create(complaint)).thenReturn(complaint);

        String jsonPayload = mapper.writeValueAsString(complaint);
        //Act
        MvcResult mockResult = mockMvc.perform(post("/complaints").contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)).andExpect(status().isCreated()).andReturn();


        //Assert
        String response = mockResult.getResponse().getContentAsString();

        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString("Pramod"));

    }


    @Test
    public void testShouldValidateFirstNameNullability() throws Exception {
        // Arrange Customer Complaint
        CustomerComplaint customerComplaint = new CustomerComplaint();

        String customerJSON = "";


        // get Employee object as a json string
        customerJSON = mapper.writeValueAsString(customerComplaint);

        when(service.create(any(CustomerComplaint.class))).thenReturn(customerComplaint);

        //Act and Expect
        MvcResult mockResult = mockMvc.perform(post("/complaints").contentType(MediaType.APPLICATION_JSON)
                .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        //Assert

        Assert.assertThat(response, org.hamcrest.Matchers.containsString("First name is Mandatory. It must not be empty and must have characters."));


    }


    @Test
    public void testShouldValidateEmptyFirstName() throws Exception {
        //Arrange
        CustomerComplaint customerComplaint = new CustomerComplaint();
        customerComplaint.setFirstName("");

        String customerJSON = "";


        when(service.create(any(CustomerComplaint.class))).thenReturn(customerComplaint);

        // Arrange Customer Complaint

        // get Employee object as a json string
        customerJSON = mapper.writeValueAsString(customerComplaint);

        //Act and Expect
        MvcResult mockResult = mockMvc.perform(post("/complaints").contentType(MediaType.APPLICATION_JSON)
                .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        //Assert

        Assert.assertThat(response, org.hamcrest.Matchers.containsString("First name is Mandatory. It must not be empty and must have characters."));


    }


    @Test
    public void testShouldValidateAgentIdNullability() throws Exception {
        // Arrange
        CustomerComplaint customerComplaint = new CustomerComplaint();
        //customerComplaint.setFirstName("");

        String customerJSON = "";


        when(service.create(any(CustomerComplaint.class))).thenReturn(customerComplaint);

        // get Employee object as a json string
        customerJSON = mapper.writeValueAsString(customerComplaint);

        //Act and Expect
        MvcResult mockResult = mockMvc.perform(post("/complaints").contentType(MediaType.APPLICATION_JSON)
                .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        //Assert

        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString("Agent id is Mandatory. It must not be empty and must be Integer."));


    }


    @Test
    public void testShouldValidateWhetherProperComplaintTypesIsEntered() throws Exception {

        // Arrange Customer Complaint
        CustomerComplaint customerComplaint = new CustomerComplaint();
        customerComplaint.setComplaintType("XYZ");

        String customerJSON = "";


        when(service.create(any(CustomerComplaint.class))).thenReturn(customerComplaint);


        // get Employee object as a json string
        customerJSON = mapper.writeValueAsString(customerComplaint);

        //Act and Expect
        MvcResult mockResult = mockMvc.perform(post("/complaints").contentType(MediaType.APPLICATION_JSON)
                .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        //Assert
        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString("Allowed Complaint Types values are INTERNET, BILL_INFO, PREPAID, POSTPAID"));


    }


    @Test
    public void testShouldValidateAllRetrievedComplaints() throws Exception {
        // Arrange
        List<CustomerComplaint> complaints = new ArrayList<>();

        when(service.getAll()).thenReturn(complaints);

        //Act and Expect
        this.mockMvc.perform(get("/complaints")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    @Test
    public void testShouldValidateRetrieveGivenComplaint() throws Exception {
        // Arrange
        String complaintId = UUID.randomUUID().toString();
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setId(complaintId);


        when(service.getById(any(String.class))).thenReturn(complaint);

        //Act and Expect
        MvcResult mockResult = this.mockMvc.perform(get("/complaints/" + complaintId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        //Assert
        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString(complaintId));


    }

    @Test
    public void testShouldNotRetrieveInvalidComplaint() throws Exception {
        // Arrange
        String complaintId = "-1";
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setId(complaintId);


        when(service.getById(any(String.class))).thenReturn(null);

        //Act and Expect
        MvcResult mockResult = this.mockMvc.perform(get("/complaints/" + complaintId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        //Assert
        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString("No record found."));


    }


    @Test
    public void testShouldRemoveComplaint() throws Exception {
        // Arrange
        String complaintId = UUID.randomUUID().toString();
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setId(complaintId);


        when(service.delete(any(String.class))).thenReturn(true);

        //Act and Expect
        MvcResult mockResult = this.mockMvc.perform(delete("/complaints/" + complaintId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString("Record removed successfully."));


    }


    @Test
    public void testShouldNotRemoveInvalidComplaint() throws Exception {
        // Arrange
        String complaintId = UUID.randomUUID().toString();
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setId(complaintId);


        when(service.delete(any(String.class))).thenReturn(false);

        //Act and Expect
        MvcResult mockResult = this.mockMvc.perform(delete("/complaints/" + complaintId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        String response = mockResult.getResponse().getContentAsString();

        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString("No record found to remove."));


    }


    @Test
    public void testShouldUpdateCustomerComplaint() throws Exception {

        String complaintId = UUID.randomUUID().toString();

        //Accept
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setId(complaintId);
        complaint.setFirstName("Pramod");
        complaint.setLastName("Nikam");
        complaint.setAgentId(1);
        complaint.setComplaintMessage("TDD is Top down or Bottom Up?");

        when(this.service.updateWhole(any(CustomerComplaint.class))).thenReturn(complaint);

        String jsonPayload = mapper.writeValueAsString(complaint);
        //Act
        MvcResult mockResult = mockMvc.perform(put("/complaints/" + complaintId).contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)).andExpect(status().isCreated()).andReturn();


        //Assert
        String response = mockResult.getResponse().getContentAsString();

        Assert.assertThat(response,
                org.hamcrest.Matchers.containsString("Pramod"));

    }


    @Test
    public void testShouldDiscardNewCustomerComplaint() throws Exception {

        String complaintId = UUID.randomUUID().toString();

        //Accept
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setId(complaintId);
        complaint.setFirstName("Pramod");
        complaint.setLastName("Nikam");
        complaint.setAgentId(1);
        complaint.setComplaintMessage("TDD is Top down or Bottom Up?");

        when(this.service.updateWhole(any(CustomerComplaint.class))).thenThrow(new NewResourceNotAllowedInPutException());

        String jsonPayload = mapper.writeValueAsString(complaint);
        //Act
        MvcResult mockResult = mockMvc.perform(put("/complaints/" + complaintId).contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)).andExpect(status().isMethodNotAllowed()).andReturn();


        //Assert
        String errorMessage = mockResult.getResponse().getErrorMessage();

        Assert.assertThat(errorMessage,
                org.hamcrest.Matchers.containsString("Existing resource is not allowed in Put Request. Please use POST method instead."));

    }


}
