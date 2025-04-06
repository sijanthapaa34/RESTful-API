package com.sijan.customermanagement.service;

import java.util.List;

import com.sijan.customermanagement.dto.CustomerDTO;

public interface CustomerService {

	CustomerDTO createCustomer(CustomerDTO customerDTO);
	CustomerDTO getCustomerById(int id);
	List<CustomerDTO> getAllCustomers();
	CustomerDTO updateCustomer(int id, CustomerDTO customerDTO);
	void deleteCustomer(int id);
}
