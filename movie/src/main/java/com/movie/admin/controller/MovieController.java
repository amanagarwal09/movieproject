package com.movie.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.admin.exception.MovieAlreadyPresentException;
import com.movie.admin.exception.MovieNotFoundException;
import com.movie.admin.model.MovieList;
import com.movie.admin.service.MovieService;

@RestController
@RequestMapping("Movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@GetMapping()
	public List<MovieList> getAllMovie() throws MovieNotFoundException
	{
		return movieService.getAllMovie();
	}
	
	@GetMapping("/{id}")
	public MovieList getMovieById(@PathVariable int id) throws MovieNotFoundException 
	{
				return movieService.getMovieById(id);
	}
	
	@PostMapping()
	public MovieList InsertNewMovie(@RequestBody MovieList movieList) throws MovieAlreadyPresentException
	{
		return movieService.InsertNewMovie(movieList);
	}
	
	@PutMapping("/{id}")
	public MovieList UpdateMovieById(@PathVariable int id , @RequestBody MovieList movieList) throws MovieNotFoundException 
	{
		return movieService.UpdateMovieById(id,movieList);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMovieById(@PathVariable int id) throws MovieNotFoundException
	{
		movieService.deleteMovieById(id);
	}
	
	@DeleteMapping()
	public void deleteAllMovie() throws MovieNotFoundException
	{
		movieService.deleteAllMovie();
	}
	
}
