package com.rishika.yummyproject.service;

import com.rishika.yummyproject.dto.CustomerRequest;
import com.rishika.yummyproject.dto.CustomerResponse;

import com.rishika.yummyproject.entity.Customer;
import com.rishika.yummyproject.mapper.CustomerMapper;
import com.rishika.yummyproject.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}
