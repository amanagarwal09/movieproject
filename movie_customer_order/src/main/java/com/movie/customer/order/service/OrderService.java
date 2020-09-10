package com.movie.customer.order.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movie.admin.exception.MovieNotFoundException;
import com.movie.admin.model.MovieList;
import com.movie.customer.order.exception.OrderNotFoundException;
import com.movie.customer.order.model.orders;
import com.movie.customer.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	RestTemplate restTemplate;

	@Transactional
	public List<orders> getallOrder() {
		return orderRepository.findAll();
	}

	@Transactional
	public String getallMovie() throws MovieNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String body = restTemplate.exchange("http://localhost:8000/Movie", HttpMethod.GET, entity, String.class)
				.getBody();
		if (body == null) {
			throw new MovieNotFoundException("No Movie present.");
		} else
			return body;
	}

	@Transactional
	public orders InsertNewOrder(orders order) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		MovieList body = restTemplate
				.exchange("http://localhost:8000/Movie/" + order.getMovieid(), HttpMethod.GET, entity, MovieList.class)
				.getBody();
		order.setAmount(body.getTicketprice() * order.getQnty());
		return orderRepository.save(order);
	}

	@Transactional
	public List<orders> getOrderByCustomerId(int custid) throws OrderNotFoundException {
		if (!orderRepository.getOrderByCustomerId(custid).isEmpty())
			return orderRepository.getOrderByCustomerId(custid);
		else {
			throw new OrderNotFoundException("No order present .");
		}
	}

	@Transactional
	public String getOrderListByCustomerId(int custid) throws OrderNotFoundException {
		List<orders> listorder = getOrderByCustomerId(custid);
		String order = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		for (orders i : listorder) {
			MovieList body = restTemplate
					.exchange("http://localhost:8000/Movie/" + i.getMovieid(), HttpMethod.GET, entity, MovieList.class)
					.getBody();
			order = order + "[Movie Name: " + body.getTittle() + " Show time =" + body.getShowtime()
					+ " Qnty Booked = " + i.getQnty() + " Amount = " + i.getAmount() + "] ";
		}
		return order;
	}

	@Transactional
	public void deleteAllOrderByCustomerId(int custid) throws OrderNotFoundException {
		if (!orderRepository.getOrderByCustomerId(custid).isEmpty()) {
			orderRepository.deleteAllOrderByCustomerId(custid);
		} else {
			throw new OrderNotFoundException("No order present to delete .");
		}
	}

	@Transactional
	public void deleteSpecificOrderByCustomerId(int custid, int movieid) throws OrderNotFoundException {
		List<orders> orderByCustomerId = orderRepository.getOrderByCustomerId(custid);
		int flag = 0;
		if (orderByCustomerId.isEmpty()) {
			throw new OrderNotFoundException("No order present to delete .");
		}
		for (orders i : orderByCustomerId) {
			if (i.getMovieid() == movieid) {
				orderRepository.deleteSpecificOrderByCustomerId(custid, movieid);
				flag = 1;
			}
		}
		if (flag == 0) {
			throw new OrderNotFoundException("No order present to delete .");
		}
	}
}
