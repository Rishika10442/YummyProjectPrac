package com.rishika.yummyproject.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.rishika.yummyproject.dto.CustomerRequest;
import com.rishika.yummyproject.dto.CustomerResponse;
import com.rishika.yummyproject.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustoemr(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody JsonNode requestBody) {
        return ResponseEntity.ok(customerService.updateCustomer(requestBody));
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("email") String email) {
        email = email.trim();
            customerService.deleteCustomer(email);
            return ResponseEntity.ok("Customer account deleted successfully");

    }
}
