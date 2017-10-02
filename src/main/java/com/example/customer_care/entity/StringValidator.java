package com.example.customer_care.entity;

import com.example.customer_care.customAnnotations.AllowedComplaintTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class StringValidator implements ConstraintValidator<AllowedComplaintTypes, String> {

    private List<String> valueList;

    @Override
    public void initialize(AllowedComplaintTypes constraintAnnotation) {
        valueList = new ArrayList<String>();
        for(String val : constraintAnnotation.acceptedValues()) {
            valueList.add(val.toUpperCase());
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!valueList.contains(value.toUpperCase())) {
            return false;
        }
        return true;
    }

}