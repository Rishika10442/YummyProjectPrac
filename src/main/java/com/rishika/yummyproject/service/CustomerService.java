package com.rishika.yummyproject.service;

import com.rishika.yummyproject.dto.CustomerRequest;
import com.rishika.yummyproject.dto.CustomerResponse;

import com.rishika.yummyproject.dto.LoginRequest;
import com.rishika.yummyproject.entity.Customer;
import com.rishika.yummyproject.exception.CustomerNotFoundException;
import com.rishika.yummyproject.filter.JWTFilter;
import com.rishika.yummyproject.helper.CustomUserDetails;
import com.rishika.yummyproject.helper.JWTHelper;
import com.rishika.yummyproject.mapper.CustomerMapper;
import com.rishika.yummyproject.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static java.lang.String.format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
   @Autowired
   private final BCryptPasswordEncoder passwordEncoder;
    private final JWTHelper jwtHelper;
    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    public String createCustomer(CustomerRequest request) {
        String encryptedPassword = passwordEncoder.encode(request.password());
        Customer customer = mapper.toEntity(request,encryptedPassword);
        repo.save(customer);
        return "Created";
    }

    public Customer getCustomer(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }
    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        boolean matches = passwordEncoder.matches(request.password(), customer.getPassword());
        if(!matches){
            return "Wrong Password or Email";
        }
        return jwtHelper.generateToken(request.email());

    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return mapper.toCustomerResponse(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user details for username: {}", username);
        Customer customer = repo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + username));
        return new CustomUserDetails(customer); // Wrapping Customer in CustomUserDetails
    }
}
