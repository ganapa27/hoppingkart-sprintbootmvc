package shoppingkart.shoppingKart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import shoppingkart.shoppingKart.dao.MerchantDao;
import shoppingkart.shoppingKart.dao.ProductDao;
import shoppingkart.shoppingKart.dto.Merchant;
import shoppingkart.shoppingKart.dto.MerchantProduct;
import shoppingkart.shoppingKart.helper.AES;
import shoppingkart.shoppingKart.helper.LoginHelper;
import shoppingkart.shoppingKart.helper.SendMailLogic;

@Service
@ResponseBody
public class MerchantService {
    @Autowired
    MerchantDao merchantDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    SendMailLogic sendMailLogic;

    public String signup(Merchant merchant, ModelMap map) {
        Merchant merchant1=merchantDao.fetchbyEmail(merchant.getEmail());
        Merchant merchant2=merchantDao.fetchbyPhone(merchant.getPhone());

        if(merchant1!=null){
            map.put("fail","Email already exists");
            return "merchantSignup";
        }
        if(merchant2!=null){
            map.put("fail", "Phone number already exists");
            return "merchantSignup";
        }
        merchant.setPassword(AES.encrypt(merchant.getPassword(), "123"));
        int otp = new Random().nextInt(100000,999999);
        merchant.setOtp(otp);
        // Logic for sending email
        sendMailLogic.sendEmail(merchant);
        merchantDao.saveData(merchant);
        map.put("success","Email sent successfully");
        map.put("id",merchant.getId());
        return "verifyOtp1";
    }

    public String verify(int id, int otp, ModelMap map) {
        Merchant merchant = merchantDao.fetchById(id);
        if(merchant==null){
            map.put("fail", "Something went wrong");
            return "home";
        }
        else{
            if(merchant.getOtp()==otp){
                merchant.setVerified(true);
                merchantDao.saveData(merchant);
                map.put("pass", "OTP verified successfully");
                return "merchantLogin";
            }
            else{
                map.put("fail", "Invalid OTP");
                map.put("id", id);
                return "verifyOtp1";
            }
        }
    }

    public String login(LoginHelper helper, ModelMap map, HttpSession session) {
        Merchant merchant = merchantDao.fetchbyEmail(helper.getEmail());
        if(merchant==null){
            map.put("fail", "Email does not exist");
            return "merchantLogin";
        }
        else{
            if (AES.decrypt(merchant.getPassword(), "123").equals(helper.getPassword())) {
                if(merchant.isVerified())
                {
                    session.setAttribute("merchant", merchant);
                    map.put("pass", "Login Successfully");
                    return "MerchantHome";
                }
                else{
                    map.put("fail", "Account Not Verified, Verify First");
                    sendMailLogic.sendEmail(merchant);
                    return "verifyOtp1";
                }
            }
            else{
                map.put("fail", "Invalid Password");
                return "MerchantLogin";
            }
        }
    }

    public String addProduct(MerchantProduct product, MultipartFile pic, ModelMap map, Merchant merchant, HttpSession session)
			throws IOException {
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

		product.setPicture(picture);
		List<MerchantProduct> list = merchant.getProducts();

		if (list == null)
			list = new ArrayList<MerchantProduct>();

		list.add(product);
		merchant.setProducts(list);
		session.setAttribute("merchant", merchantDao.saveData(merchant));
		map.put("pass", "Product Added Success");
		return "MerchantHome";
	}

    public String fetchProducts(Merchant merchant, ModelMap modelMap) {
		List<MerchantProduct> list = merchant.getProducts();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Products Available");
			return "MerchantHome";
		} else {
			modelMap.put("list", list);
			return "MerchantProducts";
		}
	}

    public String delete(int id, ModelMap modelMap, Merchant merchant, HttpSession session) {
		MerchantProduct product = productDao.findById(id);
		if (product == null) {
			modelMap.put("neg", "Something Went Wrong");
			return "home";
		} else {

			for (MerchantProduct product1 : merchant.getProducts()) {
				if (product1.getName().equals(product.getName())) {
					product = product1;
					break;
				}
			}
			merchant.getProducts().remove(product);
			Merchant merchant2 = merchantDao.saveData(merchant);
			session.setAttribute("merchant", merchant2);
			productDao.delete(product);
			modelMap.put("pos", "Product Deleted Success");
			return fetchProducts(merchant2, modelMap);
		}
	}
    
    public String edit(int id, ModelMap modelMap) {
		MerchantProduct product = productDao.findById(id);
		if (product == null) {
			modelMap.put("fail", "Something Went Wrong");
			return "home";
		} else {
			modelMap.put("product", product);
			return "EditProduct";
		}
	}

    public String editProduct(MerchantProduct product, MultipartFile pic, ModelMap map, Merchant merchant, HttpSession session)
			throws IOException {
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

		if (picture.length == 0) {
			product.setPicture(productDao.findById(product.getId()).getPicture());
		} else {
			product.setPicture(picture);
		}
		productDao.save(product);
		Merchant merchant2 = merchantDao.fetchById(merchant.getId());
		session.setAttribute("merchant", merchant2);
		map.put("pass", "Product Updated Success");
		return fetchProducts(merchant2, map);
	}
}
