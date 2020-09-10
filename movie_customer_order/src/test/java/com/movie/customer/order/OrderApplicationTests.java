package com.movie.customer.order;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.movie.customer.order.controller.OrderController;
import com.movie.customer.order.model.orders;

@SpringBootTest
@AutoConfigureMockMvc
class OrderApplicationTests {

	@Autowired
	OrderController ordercontroller;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	void contextLoadsOrder() throws Exception {
		assertNotNull(ordercontroller);
	}
	
	@Test
	void testGetOrderListByCustomerIdPresent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/order/2"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	void testGetOrderListByCustomerIdAbsent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/order/5"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Order not Found"));
	}
	
	
	@Test
	void testInsertNewOrder() throws Exception {
		orders order = new orders(10,1,3,2,500);
		Gson g = new Gson();
		String s = g.toJson(order);
		ResultActions actions = mvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(s));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.qnty").exists());
		actions.andExpect(jsonPath("$.qnty").value("2"));
	}
	
	@Test
	void TestDeleteAllOrderByCustomerIdPresent() throws Exception {
		ResultActions actions = mvc.perform(delete("/order/2"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	void TestDeleteAllOrderByCustomerIdAbsent() throws Exception {
		ResultActions actions = mvc.perform(delete("/order/10"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Order not Found"));;
	}
	
	
	@Test
	void TestDeleteSpecificOrderByCustomerIdPresent() throws Exception {
		ResultActions actions = mvc.perform(delete("/order/1/3"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	void TestDeleteSpecificOrderByCustomerIdAbsent() throws Exception {
		ResultActions actions = mvc.perform(delete("/order/1/5"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Order not Found"));;
	}
	

}
