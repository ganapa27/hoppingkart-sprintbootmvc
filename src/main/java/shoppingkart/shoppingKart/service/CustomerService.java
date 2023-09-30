package shoppingkart.shoppingKart.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import shoppingkart.shoppingKart.dao.CustomerDao;
import shoppingkart.shoppingKart.dto.Customer;
import shoppingkart.shoppingKart.helper.AES;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;
    
    public String signup(Customer customer, ModelMap map){
        Customer customer1=customerDao.fetchByEmail(customer.getEmail());
        Customer customer2=customerDao.fetchByPhone(customer.getNumber());

        if(customer1!=null){
            map.put("fail","Email already exists");
            return "customerSignup";
        }
        if(customer2!=null){
            map.put("fail", "Phone number already exists");
            return "customerSignup";
        }
        customer.setPassword(AES.encrypt(customer.getPassword(), "123456789"));
        int otp = new Random().nextInt(100000,999999);
        customer.setOtp(otp);
        // Logic for sending email
        customerDao.saveData(customer);
        map.put("success","Email sent successfully");
        return "CustomerVerifyOtp";
    }
}
