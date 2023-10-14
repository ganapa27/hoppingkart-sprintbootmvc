package shoppingkart.shoppingKart.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shoppingkart.shoppingKart.dto.Customer;
import shoppingkart.shoppingKart.repository.CustomerRepository;

@Repository
public class CustomerDao {
    @Autowired
    CustomerRepository customerRepository;

    public Customer fetchByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer fetchByPhone(long number){
        return customerRepository.findByNumber(number);
    }

    public void saveData(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer fetchById(int id) {
		return customerRepository.findById(id).orElse(null);
	}

	public List<Customer> fetchAllCustomers() {
		return customerRepository.findAll();
	}
}
