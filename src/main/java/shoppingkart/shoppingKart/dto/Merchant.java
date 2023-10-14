package shoppingkart.shoppingKart.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@Component
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String dob;
    private long phone;
    private String password;
    private int otp;
    private boolean verified;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<MerchantProduct> products;
}
