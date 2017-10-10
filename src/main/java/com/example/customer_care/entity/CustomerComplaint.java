package com.example.customer_care.entity;

import com.example.customer_care.customAnnotations.AllowedComplaintTypes;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class CustomerComplaint {

    @Id
    private String id;

    @NotNull(message = "First name is Mandatory. It must not be empty and must have characters.")
    @NotEmpty(message = "First name is Mandatory. It must not be empty and must have characters.")
    private String firstName;


    private String lastName;

    @NotNull(message = "Agent id is Mandatory. It must not be empty and must be Integer.")
    private Integer agentId;

    private String complaintMessage;

    @AllowedComplaintTypes(acceptedValues = {"BILL_INFO", "INTERNET", "PREPAID", "POSTPAID"}, message = "Allowed Complaint Types values are INTERNET, BILL_INFO, PREPAID, POSTPAID.")
    private String complaintType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAgentId() {
        return agentId;
    }


    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
