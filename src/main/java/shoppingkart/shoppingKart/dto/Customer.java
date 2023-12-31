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
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
@Component
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String dob;
    private long number;
    private String password;
    private int otp;
    private boolean verified;

    @OneToOne(cascade = CascadeType.ALL)
	ShoppingCart cart;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<ShoppingOrder> orders;
}
