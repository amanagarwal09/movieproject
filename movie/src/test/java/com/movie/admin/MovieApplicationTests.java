package com.movie.admin;

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
import com.movie.admin.controller.MovieController;
import com.movie.admin.model.MovieList;

@SpringBootTest
@AutoConfigureMockMvc
class MovieApplicationTests {

	@Autowired
	MovieController movieController;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	void contextLoadsMovies() throws Exception {
		assertNotNull(movieController);
	}

	@Test
	void testGetAllMoviePresent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/Movie"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$[0].tittle").exists());
		actions.andExpect(jsonPath("$[0].tittle").value("A"));
	}

/*	@Test
	void testGetAllMovieAbsent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/Movie"));
		actions.andExpect(status().isOk());
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Movie not Found"));;
	}
*/	
	@Test
	void testGetMovieByIdPresent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/Movie/1"));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.tittle").exists());
		actions.andExpect(jsonPath("$.tittle").value("A"));
	}

	@Test
	void testGetMovieByIdAbsent() throws Exception
	{
		ResultActions actions = mvc.perform(get("/Movie/10"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Movie not Found"));
	}
	
	@Test
	void testInsertNewMoviePresent() throws Exception {
		MovieList movie = new MovieList(1,"Abc",200.00,true,"Science","11:00 AM");
		Gson g = new Gson();
		String s = g.toJson(movie);
		ResultActions actions = mvc.perform(post("/Movie").contentType(MediaType.APPLICATION_JSON).content(s));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Movie Already Present"));
	}
	
	@Test
	void testInsertNewMovieAbsent() throws Exception {
		MovieList movie = new MovieList(20,"Abc",200.00,true,"Science","11:00 AM");
		Gson g = new Gson();
		String s = g.toJson(movie);
		ResultActions actions = mvc.perform(post("/Movie").contentType(MediaType.APPLICATION_JSON).content(s));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.tittle").exists());
		actions.andExpect(jsonPath("$.tittle").value("Abc"));
	}
	
	@Test
	void TestUpdateMovieByIdPresent() throws Exception {
		testInsertNewMovieAbsent();
		MovieList movie = new MovieList(20,"Abcd",200.00,true,"Science","11:00 AM");
		Gson g = new Gson();
		String s2 = g.toJson(movie);
		ResultActions actions = mvc.perform(put("/Movie/20").contentType(MediaType.APPLICATION_JSON).content(s2));
		actions.andExpect(status().isOk());
		actions.andExpect(jsonPath("$.tittle").exists());
		actions.andExpect(jsonPath("$.tittle").value("Abcd"));
		TestDeleteMovieByIdPresent();
	}
	
	@Test
	void TestUpdateMovieByIdAbsent() throws Exception {
		MovieList movie = new MovieList(10,"Abcde",200.00,true,"Science","11:00 AM");
		Gson g = new Gson();
		String s2 = g.toJson(movie);
		ResultActions actions = mvc.perform(get("/Movie/10").contentType(MediaType.APPLICATION_JSON).content(s2));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Movie not Found"));
	}
	
	@Test
	void TestDeleteMovieByIdPresent() throws Exception {
		ResultActions actions = mvc.perform(delete("/Movie/20"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	void TestDeleteMovieByIdAbsent() throws Exception {
		ResultActions actions = mvc.perform(delete("/Movie/10"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Movie not Found"));;
	}
	
/*	
	@Test
	void TestDeleteAllMoviePresent() throws Exception {
		ResultActions actions = mvc.perform(delete("/Movie"));
		actions.andExpect(status().isOk());
	}
	
	@Test
	void TestDeleteAllMovieAbsent() throws Exception {
		ResultActions actions = mvc.perform(delete("/Movie"));
		actions.andExpect(status().isNotFound());
		actions.andExpect(status().reason("Movie not Found"));;
	}
	*/
}
