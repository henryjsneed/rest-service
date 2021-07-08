package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Customer;
import com.example.service.CustomerService;

@Controller
@RefreshScope
public class AppController {
 
	@Value("${app.value}")
	private String inject;
    private final CustomerService customerService;
    
	@Autowired
	public AppController(CustomerService service) {
		this.customerService = service;
	}
	
    @RequestMapping("/signup")
    public String viewHomePage() {
    	return inject; //change this back to "index" value injection shouldn't be used here
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    	System.out.println("registration");
        model.addAttribute("customer", new Customer());
        return "signup_form";
    }
    
    @PostMapping("/process_register")
    public String processRegister(Customer c) {
    	return customerService.save(c);
    }
    
    @GetMapping({"/", "/shopping"})
    public String shopping(Model model) {
//        List<Customer> listCustomers = customerService.findAll();
//        model.addAttribute("listUsers", listCustomers);
        return "shopping";
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Customer> listCustomers = customerService.findAll();
        model.addAttribute("listUsers", listCustomers);
        return "users";
    }
}
