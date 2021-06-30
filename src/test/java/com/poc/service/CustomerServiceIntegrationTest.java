package com.poc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.model.Customer;
import com.poc.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTest {

	@MockBean
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Test
	public void createCustomerTest() {
		Customer customer = new Customer("ankamma", "raju");
		// given(customerRepository.save(customer)).willReturn("Hello");
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer customerResponse = customerService.createCustomer(customer);
		assertThat(customerResponse).isNotNull();

	}

	@Test
	public void getCustomerByName() {
		Customer customer = new Customer("ankamma", "raju");
		when(customerRepository.findByFirstName("ankamma")).thenReturn(customer);
		Customer customerResponse = customerService.getCustomer("ankamma");
		assertThat(customerResponse).isNotNull();
		assertThat(customerResponse.getFirstName()).isEqualTo(customer.getFirstName());

	}

}
