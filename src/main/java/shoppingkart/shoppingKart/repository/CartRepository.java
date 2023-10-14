package shoppingkart.shoppingKart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingkart.shoppingKart.dto.ShoppingCart;

public interface CartRepository extends JpaRepository<ShoppingCart, Integer> {
    
}
