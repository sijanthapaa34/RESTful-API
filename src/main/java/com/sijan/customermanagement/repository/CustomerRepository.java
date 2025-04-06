package com.sijan.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sijan.customermanagement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	boolean existsByEmail(String email);
}
