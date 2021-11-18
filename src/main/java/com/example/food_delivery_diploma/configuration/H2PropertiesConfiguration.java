package com.example.food_delivery_diploma.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:h2.properties")
@Profile("H2")
public class H2PropertiesConfiguration {
}