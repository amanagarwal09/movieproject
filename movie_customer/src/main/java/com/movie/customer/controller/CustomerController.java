package com.movie.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.customer.exception.CustomerAlreadyPresentException;
import com.movie.customer.exception.CustomerNotFoundException;
import com.movie.customer.model.Customer;
import com.movie.customer.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	
	@GetMapping("/{email}")
	public Customer getCustomerByEmail(@PathVariable String email) throws CustomerNotFoundException
	{
		return customerService.getCustomerById(email);
	}
	
	@PostMapping()
	public Customer InsertNewCustomer(@RequestBody Customer customer) throws CustomerAlreadyPresentException
	{
		return customerService.InsertNewCustomer(customer);
	}
	
	@PutMapping("/{email}")
	public Customer UpdateCustomerById(@PathVariable String email , @RequestBody Customer customer ) throws CustomerNotFoundException
	{
		int id = customerService.getCustomerById(email).getId();
		return customerService.UpdateCustomerById(id,email,customer);
	}
	
	@DeleteMapping("/{email}")
	public void deleteCustomerById(@PathVariable String email) throws CustomerNotFoundException
	{
		customerService.deleteCustomerById(email);
	}
	
}
