package shoppingkart.shoppingKart.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import shoppingkart.shoppingKart.dao.MerchantDao;
import shoppingkart.shoppingKart.dto.Merchant;
import shoppingkart.shoppingKart.helper.AES;
import shoppingkart.shoppingKart.dto.LoginHelper;
import shoppingkart.shoppingKart.helper.SendMailLogic;

@Service
@ResponseBody
public class MerchantService {
    @Autowired
    MerchantDao merchantDao;

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
        merchant.setPassword(AES.encrypt(merchant.getPassword(), "123456789"));
        int otp = new Random().nextInt(100000,999999);
        merchant.setOtp(otp);
        // Logic for sending email
        sendMailLogic.sendEmail(merchant);
        merchantDao.saveData(merchant);
        map.put("success","Email sent successfully");
        return "MerchantVerifyOtp";
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
                map.put("id", merchant.getId());
                return "merchantVerifyOtp";
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
                    map.put("id", merchant.getId());
                    return "MerchantVerifyOtp";
                }
            }
            else{
                map.put("fail", "Invalid Password");
                return "MerchantLogin";
            }
        }
    }
    
}
