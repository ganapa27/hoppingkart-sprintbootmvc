package shoppingkart.shoppingKart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shoppingkart.shoppingKart.dto.Customer;
import shoppingkart.shoppingKart.service.CustomerService;

@Controller
@RequestMapping("/customer")

public class CustomerController {
    
    @Autowired
    CustomerService customerService;

    @GetMapping("/login")
    public String customerLogin(){
        return "customerLogin";
    }

    @GetMapping("/signup")
    public String customerSignup(){
        return "customerSignup";
    }

    @PostMapping("/signup")
    public String signup(Customer customer, ModelMap map){
        return customerService.signup(customer, map);
    }
}
