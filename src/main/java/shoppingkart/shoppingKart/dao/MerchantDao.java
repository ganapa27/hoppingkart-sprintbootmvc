package shoppingkart.shoppingKart.dao;

import java.util.List;

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

    public Merchant saveData(Merchant merchant) {
        return merchantrepository.save(merchant);
    }

    public Merchant fetchById(int id) {
        return merchantrepository.findById(id).orElse(null);
    }

    public List<Merchant> fetchAllMerchants() {
		return merchantrepository.findAll();
	}

}
