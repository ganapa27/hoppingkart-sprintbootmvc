package shoppingkart.shoppingKart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")

public class CustomerController {
    
    @GetMapping("/login")
    public String customerLogin(){
        return "customerLogin";
    }

    @GetMapping("/signup")
    public String customerSignup(){
        return "customerSignup";
    }
}
