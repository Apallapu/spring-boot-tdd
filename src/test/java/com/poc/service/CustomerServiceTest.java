package com.poc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.poc.exception.CustomerNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.poc.model.Customer;
import com.poc.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerService customerService;

	@Test
	public void createCustomerTest() {
		Customer customer = new Customer("ankamma", "raju");

		when(customerRepository.save(customer)).thenReturn(customer);
		Customer customerResponse=customerService.createCustomer(customer);
		verify(customerRepository).save(customer);
		assertThat(customerResponse).isNotNull();

	}

	@Test(expected = RuntimeException.class)
	public void createCustomerInternalServerTest() {
		Customer customer = new Customer("ankamma", "raju");

		when(customerRepository.save(customer)).thenThrow(RuntimeException.class);
		Customer customerResponse=customerService.createCustomer(customer);
		assertThat(customerResponse).isNotNull();

		verify(customerRepository).save(customer);

	}
	@Test
	public void getCustomerByName() {
		Customer customer = new Customer("ankamma", "raju");
		when(customerRepository.findByFirstName("ankamma")).thenReturn(customer);
		Customer customerResponse=customerService.getCustomer("ankamma");
		assertThat(customerResponse).isNotNull();
		assertThat(customerResponse.getFirstName()).isEqualTo(customer.getFirstName());

		verify(customerRepository,atLeastOnce()).findByFirstName(anyString());
		
	}
	@Test(expected = CustomerNotFoundException.class)
	public void getCustomerByNameNotFound() {
		when(customerRepository.findByFirstName("ankamma")).thenReturn(null);
		customerService.getCustomer("ankamma");
		verify(customerRepository,atLeastOnce()).findByFirstName(anyString());

	}



}
