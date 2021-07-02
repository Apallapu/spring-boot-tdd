package com.poc.controller;

import com.poc.exception.CustomerException;
import com.poc.model.Customer;
import com.poc.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Customer", description = "the Customer API")
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Add a new Customer", description = "", tags = {"Customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created",
                    content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Customer already exists")})
    @PostMapping(value = "/customer/create", consumes = {"application/json", "application/xml"})
    public Customer createCustomer(@Parameter(description = "Customer to add. Cannot null or empty.",
            required = true, schema = @Schema(implementation = Customer.class)) @Valid @RequestBody Customer customer) {
        validateCustomer(customer);
        return customerService.createCustomer(customer);

    }

    private void validateCustomer(Customer customer) {
        if (customer.getFirstName() == null) {
            throw new CustomerException(HttpStatus.BAD_REQUEST.value(), "Bad Request");
        }
    }

    @Operation(summary = "Update an existing Customer", description = "", tags = {"Customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping(value = "/customer/update/{id}", consumes = {"application/json", "application/xml"})
    public Customer updateCustomer(@Parameter(description = "Customer to update. Cannot null or empty.",
            required = true, schema = @Schema(implementation = Customer.class)) @RequestBody Customer customer, @Parameter(description = "Id of the customer to be update. Cannot be empty.",
            required = true) @PathVariable Long id) {
        validateCustomer(customer);
        return customerService.updateCustomer(customer, id);
    }

    @Operation(summary = "Deletes a customer", description = "", tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "customer not found")})
    @DeleteMapping(path = "/customer/delete/{id}")
    public void deleteCustomer(@Parameter(description = "Id of the customer to be delete. Cannot be empty.",
            required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @Operation(summary = "Find customer by name", description = "Returns a single customer", tags = {"Customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found")})
    @GetMapping(value = "/customer", produces = {"application/json", "application/xml"})
    public Customer getCustomer(@Parameter(description = "Name of the customer to be obtained. Cannot be empty.", required = true) @RequestParam String name) {
        return customerService.getCustomer(name);

    }

}
