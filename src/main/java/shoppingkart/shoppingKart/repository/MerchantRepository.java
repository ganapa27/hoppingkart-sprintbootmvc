package shoppingkart.shoppingKart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingkart.shoppingKart.dto.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer>{
    Merchant findByEmail(String cemail);

    Merchant findByPhone(long cphone);
    
}
