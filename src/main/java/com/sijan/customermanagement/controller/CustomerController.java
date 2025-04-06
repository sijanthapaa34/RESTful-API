package com.sijan.customermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sijan.customermanagement.dto.CustomerDTO;
import com.sijan.customermanagement.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
		CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
		return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id){
		CustomerDTO customerDTO = customerService.getCustomerById(id);
		return ResponseEntity.ok(customerDTO);	
	}
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
		List<CustomerDTO> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);		
	}
	@PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
