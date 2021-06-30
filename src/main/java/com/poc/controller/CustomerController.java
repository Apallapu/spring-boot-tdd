package com.poc.controller;

import com.poc.exception.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.model.Customer;
import com.poc.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/customer/create")
	public Customer createCustomer(@RequestBody Customer customer) {
		validateCustomer(customer);
		return customerService.createCustomer(customer);

	}

	private void validateCustomer(Customer customer) {
		if(customer.getFirstName()==null){
			throw new CustomerException(HttpStatus.BAD_REQUEST.value(),"Bad Request");
		}
	}

	@PutMapping("/customer/update/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
		validateCustomer(customer);
		return customerService.updateCustomer(customer,id);
	}
	@DeleteMapping("/customer/delete/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}

	@GetMapping("/customer")
	public Customer getCustomer(@RequestParam String name) {
		return customerService.getCustomer(name);

	}

}
