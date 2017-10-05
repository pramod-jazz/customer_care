package com.example.customer_care;

import org.springframework.stereotype.Component;
import org.springframework.test.annotation.ProfileValueSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CustomSystemProfileValueSource implements ProfileValueSource {


    @Override
    public String get(String key) {
        Assert.hasText(key , "'key' must not be empty");
        String stringProfiles = System.getProperty(key);
        if(Objects.isNull(key) || StringUtils.isEmpty(stringProfiles)){
            return "embedded";
        }
        return System.getProperty(key);
    }
}
