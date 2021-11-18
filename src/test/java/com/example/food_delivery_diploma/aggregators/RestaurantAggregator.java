package com.example.food_delivery_diploma.aggregators;

import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Cuisine;
import com.example.food_delivery_diploma.entity.Restaurant;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class RestaurantAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return Restaurant.builder()
                .id(argumentsAccessor.getLong(0))
                .name(argumentsAccessor.getString(1))
                .cuisine(Cuisine.WITHOUT)
                .address(Address.builder()
                        .city(argumentsAccessor.getString(2))
                        .street(argumentsAccessor.getString(3))
                        .numOfHouse(argumentsAccessor.getInteger(4))
                        .longitude(argumentsAccessor.getFloat(5))
                        .latitude(argumentsAccessor.getFloat(6))
                        .build())
                .build();
    }
}
