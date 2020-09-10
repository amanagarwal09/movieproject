package com.movie.customer.order.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.admin.exception.MovieNotFoundException;
import com.movie.customer.order.exception.OrderNotFoundException;
import com.movie.customer.order.model.orders;
import com.movie.customer.order.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping()
	public String getallMovie() throws MovieNotFoundException {
		return orderService.getallMovie();
	}

	@PostMapping()
	public orders InsertNewOrder(@RequestBody orders order) {
		return orderService.InsertNewOrder(order);
	}

	@DeleteMapping("/{custid}")
	public void deleteAllOrderByCustomerId(@PathVariable int custid) throws OrderNotFoundException {
		orderService.deleteAllOrderByCustomerId(custid);
	}

	@DeleteMapping("/{custid}/{movieid}")
	public void deleteSpecificOrderByCustomerId(@PathVariable int custid, @PathVariable int movieid) throws OrderNotFoundException {
		orderService.deleteSpecificOrderByCustomerId(custid, movieid);
	}

	@GetMapping("/{custid}")
	public String getOrderListByCustomerId(@PathVariable int custid) throws OrderNotFoundException {
		return orderService.getOrderListByCustomerId(custid);
	}

}
