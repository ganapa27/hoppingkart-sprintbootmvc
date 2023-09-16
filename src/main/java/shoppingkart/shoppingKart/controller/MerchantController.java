package shoppingkart.shoppingKart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")

public class MerchantController {
    
    @GetMapping("/login")
    public String merchantLogin(){
        return "merchantLogin";
    }

    @GetMapping("/signup")
    public String merchantSignup(){
        return "merchantSignup";
    }

}
