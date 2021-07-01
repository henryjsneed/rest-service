package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
 
    private final CustomerService customerService;
    
	@Autowired
	public AppController(CustomerService service) {
		this.customerService = service;
	}
	
    @RequestMapping("/")
    public String viewHomePage() {
    	return "index";
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
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Customer> listCustomers = customerService.findAll();
        model.addAttribute("listUsers", listCustomers);
        return "users";
    }
}
