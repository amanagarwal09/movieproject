package com.movie.customer.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.customer.exception.CustomerAlreadyPresentException;
import com.movie.customer.exception.CustomerNotFoundException;
import com.movie.customer.model.Customer;
import com.movie.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Transactional
	public Customer getCustomerById(String email) throws CustomerNotFoundException {
		if (customerRepository.findById(email).isPresent()) {
			return customerRepository.findById(email).get();
		} else {
			throw new CustomerNotFoundException("Customer With Email " + email + " is not Present.");
		}
	}

	@Transactional
	public Customer InsertNewCustomer(Customer customer) throws CustomerAlreadyPresentException {
		if (!customerRepository.findById(customer.getEmail()).isPresent()) {
			return customerRepository.save(customer);
		}
		else
		{
			throw new CustomerAlreadyPresentException("Customer With Email " + customer.getEmail() + " is Already Present.");
		}
	}

	@Transactional
	public Customer UpdateCustomerById(int id, String email, Customer customer) throws CustomerNotFoundException {
		if (customerRepository.findById(email).isPresent()) {
			customer.setId(id);
			customer.setEmail(email);
			return customerRepository.save(customer);
		} else {
			throw new CustomerNotFoundException("Customer With Email " + email + " is not Present.");
		}
	}

	@Transactional
	public void deleteCustomerById(String email) throws CustomerNotFoundException {
		if (customerRepository.findById(email).isPresent()) {
			customerRepository.deleteById(email);
		} else {
			throw new CustomerNotFoundException("Customer With Email " + email + " is not Present.");
		}

	}

}
