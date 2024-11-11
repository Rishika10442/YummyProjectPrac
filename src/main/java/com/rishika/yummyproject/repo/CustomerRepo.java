package com.rishika.yummyproject.repo;

import com.rishika.yummyproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}