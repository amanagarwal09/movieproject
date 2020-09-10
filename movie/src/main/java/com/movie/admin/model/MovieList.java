package com.movie.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="MovieList")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieList {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "tittle")
	private String tittle;
	@Column(name = "ticketprice")
	private Double ticketprice;
	@Column(name = "active")
	private boolean active;
	@Column(name = "genre")
	private String genre;
	@Column(name = "showtime")
	private String showtime;
}
