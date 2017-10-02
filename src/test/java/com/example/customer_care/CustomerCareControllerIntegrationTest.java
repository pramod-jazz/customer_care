package com.example.customer_care;

import com.example.customer_care.controller.CustomerCareController;
import com.example.customer_care.entity.CustomerComplaint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = CustomerCareController.class)
public class CustomerCareControllerIntegrationTest {
    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;


    // Arrange
    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    @Test
    @Ignore
    public void testShouldCreateCustomerComplaint() {

        //Arrange CustomerComplaint
        //Customer


    }

    @Test
    @Ignore
    public void testShouldValidateCustomerComplaint() {


        // Arrange Customer Complaint
        CustomerComplaint customerComplaint = new CustomerComplaint();
        String customerJSON = "";

        ObjectMapper mapper = new ObjectMapper();
        try {
            // get Employee object as a json string
            customerJSON = mapper.writeValueAsString(customerComplaint);

        } catch (IOException e) {

            e.printStackTrace();
        }

        // Act
        //mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(""));

        try {
            //  mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(customerJSON)).andExpect(status().)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testShouldValidateFirstNameNullability() {

        CustomerComplaint customerComplaint = new CustomerComplaint();

        String customerJSON = "";

        ObjectMapper mapper = new ObjectMapper();

        // Arrange Customer Complaint

        try {
            // get Employee object as a json string
            customerJSON = mapper.writeValueAsString(customerComplaint);

            //Act and Expect
            MvcResult mockResult = mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
                    .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

            String response = mockResult.getResponse().getContentAsString();

            //Assert
            Assert.assertThat(response, org.hamcrest.Matchers.containsString("First name is Mandatory. It must not be empty and must have characters."));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testShouldValidateEmptyFirstName() {

        CustomerComplaint customerComplaint = new CustomerComplaint();
        customerComplaint.setFirstName("");

        String customerJSON = "";

        ObjectMapper mapper = new ObjectMapper();

        // Arrange Customer Complaint

        try {
            // get Employee object as a json string
            customerJSON = mapper.writeValueAsString(customerComplaint);

            //Act and Expect
            MvcResult mockResult = mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
                    .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

            String response = mockResult.getResponse().getContentAsString();

            //Assert
            Assert.assertThat(response, org.hamcrest.Matchers.containsString("First name is Mandatory. It must not be empty and must have characters."));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testShouldValidateAgentIdNullability() {

        CustomerComplaint customerComplaint = new CustomerComplaint();
        //customerComplaint.setFirstName("");

        String customerJSON = "";

        ObjectMapper mapper = new ObjectMapper();

        // Arrange Customer Complaint

        try {
            // get Employee object as a json string
            customerJSON = mapper.writeValueAsString(customerComplaint);

            //Act and Expect
            MvcResult mockResult = mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
                    .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

            String response = mockResult.getResponse().getContentAsString();

            //Assert
            Assert.assertThat(response,
                    org.hamcrest.Matchers.containsString("Agent id is Mandatory. It must not be empty and must be Integer."));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testShouldValidateWhetherProperComplaintTypesIsEntered() {

        CustomerComplaint customerComplaint = new CustomerComplaint();
        customerComplaint.setComplaintType("XYZ");

        String customerJSON = "";

        ObjectMapper mapper = new ObjectMapper();

        // Arrange Customer Complaint

        try {
            // get Employee object as a json string
            customerJSON = mapper.writeValueAsString(customerComplaint);

            //Act and Expect
            MvcResult mockResult = mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
                    .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

            String response = mockResult.getResponse().getContentAsString();

            //Assert
            Assert.assertThat(response,
                    org.hamcrest.Matchers.containsString("Allowed Complaint Types values are INTERNET, BILL_INFO, PREPAID, POSTPAID"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
