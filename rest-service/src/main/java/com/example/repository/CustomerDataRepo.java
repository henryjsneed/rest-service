package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Customer;

@Repository
public interface CustomerDataRepo extends JpaRepository<Customer, Long> {
//	  @Query("SELECT u FROM Customer u WHERE u.email = ?1")
	  Customer findByEmail(String email);
	//  Customer findById(long id);
}
//PagingAndSortingRepository is an interface that extends CrudInterface
//CrudInterface declares a number of methods like findById, saveAll, findAllById,
//and other methods including findAll. PagingAndSortingRepository is an interface
//that extends it, implementing findAll (but it is just an interface so it 
//still is only declaring it). However, at runtime, Spring REST automatically 
//creates an implementation for this interface and Then it uses the @RepositoryRestResource 
//annotation to direct Spring MVC to create RESTful endpoints at /people.
//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
//public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
//
//List<Customer> findByLastName(@Param("name") String name);
//
//Customer findById(long id);
//}