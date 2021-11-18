package com.example.food_delivery_diploma.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mySql.properties")
@Profile("MySql")
public class MySQLPropertiesConfiguration {
}
