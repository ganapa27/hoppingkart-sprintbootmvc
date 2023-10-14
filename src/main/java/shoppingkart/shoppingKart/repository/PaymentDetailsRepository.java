package shoppingkart.shoppingKart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shoppingkart.shoppingKart.dto.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {
    
}
