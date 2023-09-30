package shoppingkart.shoppingKart.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ResponseBody;

import shoppingkart.shoppingKart.dao.MerchantDao;
import shoppingkart.shoppingKart.dto.Merchant;
import shoppingkart.shoppingKart.helper.AES;

@Service
@ResponseBody
public class MerchantService {
    @Autowired
    MerchantDao merchantDao;

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
        merchant.setPassword(AES.encrypt(merchant.getPassword(), "123456789"));
        int otp = new Random().nextInt(100000,999999);
        merchant.setOtp(otp);
        // Logic for sending email
        merchantDao.saveData(merchant);
        map.put("success","Email sent successfully");
        return "MerchantVerifyOtp";
    }
    
}
