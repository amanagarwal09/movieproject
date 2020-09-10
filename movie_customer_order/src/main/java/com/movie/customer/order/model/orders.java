package com.movie.customer.order.model;

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
@Table(name="orders")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class orders {
	@Id
	@Column(name = "slno")
	private int slno;
	@Column(name = "custid")
	private int custid;
	@Column(name = "movieid")
	private int movieid;
	@Column(name = "qnty")
	private int qnty;
	@Column(name = "amount")
	private double amount;
	
}
