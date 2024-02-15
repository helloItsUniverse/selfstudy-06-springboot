package com.ohgiraffers.autoconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Value("${test.value}")
    public String value;

    @Bean
    public Object propertyReadTest() {
        System.out.println("value = " + value);

        return new Object();
    }
}
