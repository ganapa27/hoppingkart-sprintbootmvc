package shoppingkart.shoppingKart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingkart.shoppingKart.dto.CustomerProduct;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Integer> {
    
}
