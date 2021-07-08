package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.model.Customer;
import com.example.repository.CustomerDataRepo;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerDataRepoTest {
 
    @Autowired
    private TestEntityManager entityManager;
     
//    @Autowired
//    private CustomerDataRepo repo;
    @Autowired
    private CustomerDataRepo repo;
     
    // test methods go below
    @Test
    public void testCreateUser() {
        Customer customer = new Customer();
        customer.setEmail("henryjsneed@gmail.com");
        customer.setPassword("password123");
        customer.setFirstName("Henry");
        customer.setLastName("Sneed");
         
        Customer savedCustomer = repo.save(customer);
         
        Customer existCustomer = entityManager.find(Customer.class, savedCustomer.getId());
         
        assertThat(customer.getEmail()).isEqualTo(existCustomer.getEmail());
         
    }
    
    @Test
    public void	testFindByEmail() {
    	String email = "this@gmail.com";
    	Customer c = repo.findByEmail(email);
    	assertThat(c).isNotNull();
    }
}