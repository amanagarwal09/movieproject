package com.movie.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Movie Already Present")
public class MovieAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public MovieAlreadyPresentException(String msg)
	{
		super(msg);
	}
}
