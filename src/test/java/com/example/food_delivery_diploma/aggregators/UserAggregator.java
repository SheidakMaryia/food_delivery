package com.example.food_delivery_diploma.aggregators;

import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Role;
import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.entity.User;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class UserAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return User.builder()
                .id(argumentsAccessor.getLong(0))
                .name(argumentsAccessor.getString(1))
                .username(argumentsAccessor.getString(2))
                .password(argumentsAccessor.getString(3))
                .telephone(Telephone.builder()
                        .id(argumentsAccessor.getLong(4))
                        .number(argumentsAccessor.getString(5))
                        .build())
                .role(Role.USER)
                .address(Address.builder()
                        .city(argumentsAccessor.getString(6))
                        .street(argumentsAccessor.getString(7))
                        .numOfHouse(argumentsAccessor.getInteger(8))
                        .longitude(argumentsAccessor.getFloat(9))
                        .latitude(argumentsAccessor.getFloat(10))
                        .build())
                .build();

    }
}
