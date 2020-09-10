package com.movie.admin.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.admin.exception.MovieAlreadyPresentException;
import com.movie.admin.exception.MovieNotFoundException;
import com.movie.admin.model.MovieList;
import com.movie.admin.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Transactional
	public List<MovieList> getAllMovie() throws MovieNotFoundException
	{
		if(!movieRepository.findAll().isEmpty())
		{
			return movieRepository.findAll();
		}
		else
		{
			throw new MovieNotFoundException("No Movie Present.");
		}
	}
	
	@Transactional
	public MovieList getMovieById(int id) throws MovieNotFoundException
	{
		if(movieRepository.findById(id).isPresent())
		{
			return movieRepository.findById(id).get();
		}
		else
		{
			throw new MovieNotFoundException("Movie With Id "+id+ " is not Present.");
		}
	}
	
	@Transactional
	public MovieList InsertNewMovie(MovieList movieList) throws MovieAlreadyPresentException
	{
		if(!movieRepository.findById(movieList.getId()).isPresent())
		{
			return movieRepository.save(movieList);
		}
		else
		{
			throw new MovieAlreadyPresentException("Movie With Id "+movieList.getId()+ " is already Present.");
		}
	}
	@Transactional
	public MovieList UpdateMovieById(int id , MovieList movieList) throws MovieNotFoundException 
	{
		if(movieRepository.findById(id).isPresent())
		{
			movieList.setId(id);
			return movieRepository.save(movieList);
		}
		else
		{
			throw new MovieNotFoundException("Movie With Id "+id+ " is not Present.");
		}
	}
	
	@Transactional
	public void deleteMovieById(int id) throws MovieNotFoundException
	{
		if(movieRepository.findById(id).isPresent())
		{
			movieRepository.deleteById(id);
		}
		else
		{
			throw new MovieNotFoundException("Movie With Id "+id+ " is not Present.");
		}
		
	}
	
	@Transactional
	public void deleteAllMovie() throws MovieNotFoundException
	{
		if(!movieRepository.findAll().isEmpty())
		{
		movieRepository.deleteAll();
		}
		else
		{
			throw new MovieNotFoundException("No Movie Present.");
		}
	
}
}
