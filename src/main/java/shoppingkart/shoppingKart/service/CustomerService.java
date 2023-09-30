package shoppingkart.shoppingKart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoppingkart.shoppingKart.dao.CustomerDao;
import shoppingkart.shoppingKart.dto.Customer;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;
    
    public String signup(Customer customer){
        Customer customer1=customerDao.fetchByEmail(customer.getEmail());
        Customer customer2=customerDao.fetchByPhone(customer.getNumber());
        if(customer1!=null && customer2!=null){
            customerDao.save(customer);
            return "Success";
        }
        return "Failed";
    }
}
