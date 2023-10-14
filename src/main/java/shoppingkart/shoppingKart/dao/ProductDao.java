package shoppingkart.shoppingKart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import shoppingkart.shoppingKart.dto.CustomerProduct;
import shoppingkart.shoppingKart.dto.MerchantProduct;
import shoppingkart.shoppingKart.dto.PaymentDetails;
import shoppingkart.shoppingKart.dto.ShoppingCart;
import shoppingkart.shoppingKart.repository.CartRepository;
import shoppingkart.shoppingKart.repository.CustomerProductRepository;
import shoppingkart.shoppingKart.repository.PaymentDetailsRepository;
import shoppingkart.shoppingKart.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CustomerProductRepository customerProductRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	PaymentDetailsRepository detailsRepository;
	
	public MerchantProduct findById(int id) {
		return productRepository.findById(id).orElse(null);
	}

	public void delete(MerchantProduct product) {
		productRepository.delete(product);
	}

	public void save(MerchantProduct product) {
		productRepository.save(product);
	}

	public List<MerchantProduct> fetchAllProducts() {
		return productRepository.findAll();
	}

	public List<MerchantProduct> fetchAprovedProducts() {
		return productRepository.findByApprovedTrue();
	}
	
	public void save(CustomerProduct product) {
		customerProductRepository.save(product);
	}
	
	public void save(ShoppingCart cart) {
		cartRepository.save(cart);
	}

	public void delete(CustomerProduct customerProduct) {
		customerProductRepository.delete(customerProduct);
	}
	
	public PaymentDetails saveDetails(PaymentDetails details)
	{
		return detailsRepository.save(details);
	}
	
	public PaymentDetails find(int id)
	{
		return detailsRepository.findById(id).orElse(null);
	}
}