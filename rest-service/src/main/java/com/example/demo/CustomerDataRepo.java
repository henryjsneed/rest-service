package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerDataRepo extends JpaRepository<Customer, Long> {
//	  @Query("SELECT u FROM Customer u WHERE u.email = ?1")
	  Customer findByEmail(String email);
	//  Customer findById(long id);
}
