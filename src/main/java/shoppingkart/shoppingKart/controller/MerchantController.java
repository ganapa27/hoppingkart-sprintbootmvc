package shoppingkart.shoppingKart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shoppingkart.shoppingKart.dto.Merchant;
import shoppingkart.shoppingKart.service.MerchantService;

@Controller
@RequestMapping("/merchant")

public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @GetMapping("/login")
    public String merchantLogin(){
        return "merchantLogin";
    }

    @GetMapping("/signup")
    public String merchantSignup(){
        return "merchantSignup";
    }

    @PostMapping("/signup")
    public String signup(Merchant merchant,ModelMap map){
        return merchantService.signup(merchant, map);
    }
}
