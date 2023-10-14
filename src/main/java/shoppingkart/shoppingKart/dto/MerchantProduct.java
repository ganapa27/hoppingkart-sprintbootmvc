package shoppingkart.shoppingKart.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Component
@Entity
@Data
public class MerchantProduct {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int stock;
	private double price;
	private String category;
	boolean approved;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	byte[] picture;
}
