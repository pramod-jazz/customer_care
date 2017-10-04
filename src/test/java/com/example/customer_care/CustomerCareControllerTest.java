package com.example.customer_care;

import com.example.customer_care.controller.CustomerCareController;
import com.example.customer_care.entity.CustomerComplaint;
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


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerCareControllerTest {

    @Autowired
    private CustomerCareController subject;



    @MockBean
    private CustomerCareRepository customerCareRepository;


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }


    @Test
    public void testCustomerComplaintCreation(){
       //Accept
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setFirstName("Pramod");
        complaint.setLastName("Nikam");
        complaint.setAgentId(1);
        complaint.setComplaintMessage("TDD is Top down or Bottom Up?");

        when(this.customerCareRepository.save(complaint)).thenReturn(complaint);

        //Act
        subject.createComplaint(complaint);

        //Assert
        Assert.assertNotNull(complaint);
        Assert.assertNull(complaint.getId());





    }

   // @Test
    public void testShouldCreateCustomerComplaint() throws Exception{
        System.out.print(" ***** Whether this works!");
        //Accept
        CustomerComplaint complaint = new CustomerComplaint();
        complaint.setFirstName("Pramod");
        complaint.setLastName("Nikam");
        complaint.setAgentId(1);
        complaint.setComplaintMessage("TDD is Top down or Bottom Up?");

        ObjectMapper mapper = new ObjectMapper();

        // Arrange Customer Complaint


        // get Employee object as a json string
        String customerJSON = mapper.writeValueAsString(complaint);
        // Act
        // given(customerCareService.create(complaint)).willReturn(complaint);
     //   when(customerCareService.create(any(CustomerComplaint.class))).thenReturn(complaint);

        MvcResult mockResult = mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON)
                .content(customerJSON)).andExpect(status().isBadRequest()).andReturn();

        //Verify
       // verify(customerCareService).create(complaint);
       // verifyNoMoreInteractions(customerCareService);

        Assert.assertNotNull(complaint);
        Assert.assertNotNull(complaint.getId());
        //Assert.assertTrue(complaint.getId() instanceof Integer);

    }


}
