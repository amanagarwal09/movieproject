package com.movie.customer;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.movie.customer.controller.CustomerController;
import com.movie.customer.model.Customer;

@AutoConfigureMockMvc
@SpringBootTest
class CustomerApplicationTests {
	
	@Autowired
	CustomerController customerController;
	
	@Autowired
	MockMvc mvc;

	@Test
	void contextLoadsCustomer() throws Exception {
		assertNotNull(customerController);
	}
	
	@Test
	void testGetCustomerByEmailPresent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/customer/B@c.com"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("Abc"));
	}
	
	@Test
	void testGetCustomerByEmailAbsent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/customer/ddd@c.com"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Customer not Found"));
	}
	
	@Test
	void testInsertNewCustomerPresent() throws Exception {
		Customer customer = new Customer(1,"A","B@c.com","aman");
		Gson g = new Gson();
		String s = g.toJson(customer);
		ResultActions actions = mvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(s));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Customer Already Present"));
	}
	
	@Test
	void testInsertNewCustomerAbsent() throws Exception {
		Customer customer = new Customer(10,"A","Aad@c.com","aman");
		Gson g = new Gson();
		String s = g.toJson(customer);
		ResultActions actions = mvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(s));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("A"));

	}
	
	@Test
	void TestUpdateCustomerByEmailPresent() throws Exception {
		Customer customer = new Customer(1,"Abc","B@c.com","aman");
		Gson g = new Gson();
		String s2 = g.toJson(customer);
		ResultActions actions = mvc.perform(put("/customer/B@c.com").contentType(MediaType.APPLICATION_JSON).content(s2));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.name").exists());
		actions.andExpect(jsonPath("$.name").value("Abc"));
		
	}
	
	@Test
	void TestUpdateCustomerByEmailAbsent() throws Exception {
		Customer customer = new Customer(20,"Abc","Ad@c.com","aman");
		Gson g = new Gson();
		String s2 = g.toJson(customer);
		ResultActions actions = mvc.perform(get("/customer/Ad@c.com").contentType(MediaType.APPLICATION_JSON).content(s2));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Customer not Found"));
	}
	
	@Test
	void TestDeleteCustomerByEmailPresent() throws Exception {
		
		Customer customer = new Customer(11,"A","A@c.com","aman");
		Gson g = new Gson();
		String s = g.toJson(customer);
		
		@SuppressWarnings("unused")
		ResultActions actions1 = mvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(s));
		
		ResultActions actions = mvc.perform(delete("/customer/A@c.com"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	void TestDeleteCustomerByEmailAbsent() throws Exception {
		ResultActions actions = mvc.perform(delete("/customer/dddd@c.com"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Customer not Found"));;
	}
	

}
