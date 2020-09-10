package com.movie.customer.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movie.customer.order.model.orders;

@Repository
public interface OrderRepository extends JpaRepository<orders, Integer> {

	  @Query(value = "select o from orders o where o.custid = ?1") 
	  public List<orders> getOrderByCustomerId(int custid);
	 
	
	  @Modifying
	  @Query(value = "delete from orders o where o.custid=?1 ") 
	  public void deleteAllOrderByCustomerId(int custid);
	
	  @Modifying
	  @Query(value = "delete from orders o where o.custid=?1 and o.movieid=?2")
	  public void deleteSpecificOrderByCustomerId(int custid,int movieid);
	  
	 
}
