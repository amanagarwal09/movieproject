package com.movie.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Customer")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	@NotNull
	private String name;
	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
}
