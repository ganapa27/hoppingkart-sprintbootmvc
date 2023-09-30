package shoppingkart.shoppingKart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shoppingkart.shoppingKart.dto.Merchant;
import shoppingkart.shoppingKart.repository.MerchantRepository;

@Repository //to specify where we are doing database logic operations
public class MerchantDao {
    @Autowired
    MerchantRepository merchantrepository;

    public Merchant fetchbyEmail(String email) {
        return merchantrepository.findByEmail(email);
    }

    public Merchant fetchbyPhone(long phone) {
        return merchantrepository.findByPhone(phone);
    }

    public void saveData(Merchant merchant) {
        merchantrepository.save(merchant);
    }
  
}
