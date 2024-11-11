package com.rishika.yummyproject.mapper;


import com.rishika.yummyproject.dto.CustomerRequest;
import com.rishika.yummyproject.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
}

