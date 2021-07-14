package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// indicates this class is a JPA entity
@Entity
public class Customer {

  @Id // ensures JPA recognizes id as the object's ID
  @GeneratedValue(strategy=GenerationType.AUTO) // indicates value should be generated automatically
  private Long id;
  
  @Column(name = "Customer_First", nullable = false, length = 20)
  private String firstName;

  @Column(name = "Customer_Last", nullable = false, length = 20)
  private String lastName;
  
  @Column(nullable = false, unique = true, length = 45)
  private String email;
   
  @Column(nullable = false, length = 64)
  private String password;

  
  public Customer() {}
  
  public Customer(String firstName, String lastName, String email, String password) {
    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setPassword(password);
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

  public Long getId() {
    return id;
  }
  
  public void setEmail(String email) {
	  this.email = email;
  }
  
  public String getEmail() {
	  return email;
  }
  
  public String getUsername() {
	  return email;
  }
  
  public void setPassword(String pass) {
	  this.password = pass;
  }
  
  public String getPassword() {
	  return password;
  }

  public void setFirstName(String first) {
	  this.firstName = first;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setLastName(String last) {
	  this.lastName = last;
  }
  public String getLastName() {
    return lastName;
  }
}