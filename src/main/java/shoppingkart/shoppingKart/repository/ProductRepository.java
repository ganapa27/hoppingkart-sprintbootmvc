package shoppingkart.shoppingKart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import shoppingkart.shoppingKart.dto.MerchantProduct;

public interface ProductRepository extends JpaRepository<MerchantProduct, Integer> {
    List<MerchantProduct> findByApprovedTrue();
}
