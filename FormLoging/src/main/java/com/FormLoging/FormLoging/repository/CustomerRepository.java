package com.FormLoging.FormLoging.repository;

import com.FormLoging.FormLoging.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByUsername(String username);
}
