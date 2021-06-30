package com.poc.service;

import com.poc.exception.CustomerException;
import com.poc.exception.CustomerNotFoundException;
import com.poc.model.Customer;
import com.poc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {

        return customerRepository.save(customer);

    }


    public Customer getCustomer(String name) {

        Customer customer = customerRepository.findByFirstName(name);
        if (customer == null) {
            throw new CustomerNotFoundException(HttpStatus.NOT_FOUND.value(), "Customer not found");
        }
        return customerRepository.findByFirstName(name);
    }

    public Customer updateCustomer(Customer customer, Long id) {
        return new Customer("ankamma", "raju");

    }

    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(HttpStatus.NOT_FOUND.value(), "Customer not found"));
        customerRepository.delete(customer);
    }

}
