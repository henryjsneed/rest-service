package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
public class CustomerDetailsService implements UserDetailsService {
 
    @Autowired
    private CustomerDataRepo customerRepo;
     
    @Override
    public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomerDetails(customer);
    }
 
}