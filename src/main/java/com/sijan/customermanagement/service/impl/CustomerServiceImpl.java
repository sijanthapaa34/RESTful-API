package com.sijan.customermanagement.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sijan.customermanagement.dto.CustomerDTO;
import com.sijan.customermanagement.exception.ResourceNotFoundException;
import com.sijan.customermanagement.exception.EmailAlreadyExistsException;
import com.sijan.customermanagement.model.Customer;
import com.sijan.customermanagement.repository.CustomerRepository;
import com.sijan.customermanagement.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		// Check if email already exists
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + customerDTO.getEmail());
        }
        
        // Convert DTO to Entity
        Customer customer = mapToEntity(customerDTO);
        
        // Save customer
        Customer savedCustomer = customerRepository.save(customer);
        
        // Convert Entity to DTO
        return mapToDTO(savedCustomer);
	}

	@Override
	public CustomerDTO getCustomerById(int id) {
		Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return mapToDTO(customer);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
	}

	@Override
	public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
		Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        
        // Check if email already exists and is not the current customer's email
        if (!customer.getEmail().equals(customerDTO.getEmail()) && 
                customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + customerDTO.getEmail());
        }
        
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        
        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDTO(updatedCustomer);
	}

	@Override
	public void deleteCustomer(int id) {
		Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
	}
	
	  // Convert Entity to DTO
    private CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }
    
    // Convert DTO to Entity
    private Customer mapToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }
	
	
}
