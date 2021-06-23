//package com.example.demo;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.sun.javafx.collections.MappingChange.Map;
//
//@Controller
//public class HomeController {
//
//    private Map<String, LocalDateTime> usersLastAccess = (Map<String, LocalDateTime>) new HashMap();
//
//    @GetMapping("/")
//    public String getCurrentUser(@AuthenticationPrincipal Customer customer, Model model) {
//        String username = customer.getUsername();
//        
//        model.addAttribute("username", username);
//
//        return "home";
//    }
//
//	@GetMapping("/")
//	public String getCurrentUser(@AuthenticationPrincipal OidcUser user, Model model) {
//	    String email = user.getEmail();
//	
//	    model.addAttribute("email", email);
//	    model.addAttribute("lastAccess", usersLastAccess.get(email));
//	    model.addAttribute("firstName", user.getGivenName());
//	    model.addAttribute("lastName", user.getFamilyName());
//	
//	    usersLastAccess.put(email, LocalDateTime.now());
//	
//	    return "home";
//	}
//}