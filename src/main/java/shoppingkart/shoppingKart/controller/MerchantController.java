package shoppingkart.shoppingKart.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import shoppingkart.shoppingKart.dto.Merchant;
import shoppingkart.shoppingKart.dto.MerchantProduct;
import shoppingkart.shoppingKart.helper.LoginHelper;
import shoppingkart.shoppingKart.service.MerchantService;

@Controller
@RequestMapping("/merchant")

public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @Autowired
    MerchantProduct product;

	@GetMapping("/home")
	public String loadHome(HttpSession session, ModelMap modelMap) {
		if (session.getAttribute("merchant") != null) {
			return "MerchantHome";
		} else {
			modelMap.put("neg", "Inavlid Session");
			return "merchantLogin";
		}
	}

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

    @PostMapping("/verify-otp")
    public String verify(@RequestParam int id, @RequestParam int otp, ModelMap map){
        return merchantService.verify(id, otp, map);
    }

    @PostMapping("/login")
    public String login(LoginHelper helper,ModelMap map,HttpSession session){
        return merchantService.login(helper, map,session);
    }

    @GetMapping("/add-product")
	public String addProduct(ModelMap map, HttpSession session) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			map.put("product", product);
			return "AddProduct";
		} else {
			map.put("neg", "Invalid Session");
			return "home";
		}
	}

    @PostMapping("/add-product")
	public String addProduct(@Validated MerchantProduct product, BindingResult result, @RequestParam MultipartFile pic,
			ModelMap map, HttpSession session) throws IOException {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			if (result.hasErrors())
				return "AddProduct";
			else {
				return merchantService.addProduct(product, pic, map, merchant, session);
			}
		} else {
			map.put("neg", "Invalid Session");
			return "home";
		}
	}

    @GetMapping("/fetch-products")
	public String fetchProducts(HttpSession session, ModelMap modelMap) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			return merchantService.fetchProducts(merchant, modelMap);
		} else {
			modelMap.put("fail", "Invalid Session");
			return "home";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id, HttpSession session, ModelMap modelMap) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			return merchantService.delete(id, modelMap, merchant, session);
		} else {
			modelMap.put("neg", "Invalid Session");
			return "home";
		}
	}

	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable int id, HttpSession session, ModelMap modelMap) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			return merchantService.edit(id, modelMap);
		} else {
			modelMap.put("neg", "Invalid Session");
			return "home";
		}
	}

	@PostMapping("/update-product")
	public String updateProduct(@Validated MerchantProduct product, BindingResult result, @RequestParam MultipartFile pic,
			ModelMap map, HttpSession session) throws IOException {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			if (result.hasErrors())
				return "EditProduct";
			else {
				return merchantService.editProduct(product, pic, map, merchant, session);
			}
		} else {
			map.put("neg", "Invalid Session");
			return "home";
		}
	}
}
