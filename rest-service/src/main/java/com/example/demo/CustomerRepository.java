package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// PagingAndSortingRepository is an interface that extends CrudInterface
// CrudInterface declares a number of methods like findById, saveAll, findAllById,
// and other methods including findAll. PagingAndSortingRepository is an interface
// that extends it, implementing findAll (but it is just an interface so it 
// still is only declaring it). However, at runtime, Spring REST automatically 
// creates an implementation for this interface and Then it uses the @RepositoryRestResource 
// annotation to direct Spring MVC to create RESTful endpoints at /people.
//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
//public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
//
//  List<Customer> findByLastName(@Param("name") String name);
//
//  Customer findById(long id);
//}

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

  @Query("SELECT u FROM Customer u WHERE u.email = ?1")
  Customer findByEmail(String email);
//  Customer findById(long id);
  
  
}
