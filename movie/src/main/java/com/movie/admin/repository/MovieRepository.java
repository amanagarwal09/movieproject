package com.movie.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.admin.model.MovieList;

@Repository
public interface MovieRepository extends JpaRepository<MovieList,Integer> {
	

}
