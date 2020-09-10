package com.movie.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Customer Already Present")
public class CustomerAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerAlreadyPresentException(String msg)
	{
		super(msg);
	}
}
