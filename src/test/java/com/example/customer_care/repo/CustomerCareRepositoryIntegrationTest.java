package com.example.customer_care.repo;

import com.example.customer_care.CustomSystemProfileValueSource;
import com.example.customer_care.entity.CustomerComplaint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@ProfileValueSourceConfiguration(value = CustomSystemProfileValueSource.class)
@RunWith(SpringRunner.class)
@IfProfileValue(name= "spring.profiles.active" , value = "embedded")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerCareRepositoryIntegrationTest {

    @Autowired
    private CustomerCareRepository customerCareRepository;



    @Test
    public void checkSave(){
        //Arrange
       String complaintId =  UUID.randomUUID().toString();
        CustomerComplaint complaint = getCustomerComplaint(complaintId);

        // Act
        CustomerComplaint returnedComplaint = customerCareRepository.save(complaint);

        // Assert
        Assert.assertNotNull(returnedComplaint.getId());
        Assert.assertEquals(complaint.getId(), complaintId);



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

    @Test
    public void checkDelete(){
        //Arrange
        String complaintId =  UUID.randomUUID().toString();
        CustomerComplaint complaint = getCustomerComplaint(complaintId);

        // Act

        CustomerComplaint returnedComplaint = customerCareRepository.save(complaint);
        customerCareRepository.delete(complaintId);
        CustomerComplaint rechechComplaint = customerCareRepository.findOne(complaintId);


        // Assert
        Assert.assertNull(rechechComplaint);




    }


    @Test
    public void checkUpdate(){
        //Arrange
        String complaintId =  UUID.randomUUID().toString();
        CustomerComplaint complaint = getCustomerComplaint(complaintId);

        // Act

        CustomerComplaint returnedComplaint = customerCareRepository.save(complaint);
        returnedComplaint.setFirstName("Pramod-changed");
        customerCareRepository.save(returnedComplaint);
        CustomerComplaint recheckComplaint = customerCareRepository.findOne(complaintId);


        // Assert
        Assert.assertNotNull(recheckComplaint);
        Assert.assertEquals(recheckComplaint.getFirstName() , "Pramod-changed" );




    }

    @Test
    public void checkRead(){
        //Arrange
        String complaintId =  UUID.randomUUID().toString();
        CustomerComplaint complaint = getCustomerComplaint(complaintId);

        // Act

        CustomerComplaint returnedComplaint = customerCareRepository.save(complaint);



        // Assert
        Assert.assertNotNull(returnedComplaint);
        Assert.assertEquals(returnedComplaint.getFirstName() , "Pramod" );




    }


}
