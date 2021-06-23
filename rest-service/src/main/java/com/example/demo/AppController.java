package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
 
    @Autowired
    private CustomerDataRepo customerRepo;
     
//    @GetMapping("/")
    @RequestMapping("/")
  //  @ResponseBody
    public String viewHomePage() {
//    	ModelAndView modelAndView = new ModelAndView("index.html");
//        modelAndView.addObject("index");
//
//        //...
//
//        return modelAndView;
    	return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup_form";
    }
    
    @PostMapping("/process_register")
    public String processRegister(Customer customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
         
        customerRepo.save(customer);
         
        return "register_success";
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<Customer> listCustomers = customerRepo.findAll();
        model.addAttribute("listUsers", listCustomers);
         
        return "users";
    }
}
