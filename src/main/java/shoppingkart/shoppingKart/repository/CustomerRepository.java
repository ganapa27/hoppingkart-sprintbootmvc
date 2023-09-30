package shoppingkart.shoppingKart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingkart.shoppingKart.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Customer findByEmail(String email);

    Customer findByNumber(long number);
    
}
