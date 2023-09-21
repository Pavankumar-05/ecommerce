package com.dnb.ecommerce.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.ecommerce.utils.CustomProductIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * The Product entity represents a product in the e-commerce system.
 * It's used for storing information about products, including name, price, description, expiry date and category. 
 */
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "product_seq")
	@GenericGenerator(name = "product_seq",
	type = CustomProductIdGenerator.class,
	parameters = {
			@Parameter(name= CustomProductIdGenerator.INCREMENT_PARAM,value = "50"),
			@Parameter(name = CustomProductIdGenerator.NUMBER_FORMAT_PARAMETER,value="%05d"),
			@Parameter(name= CustomProductIdGenerator.VALUE_PREFIX_PARAMETER,value="PROD_")
	})
	private String productId;
	@Column(nullable=false,unique=true)
	@NotBlank(message="Can't be blank!!!")
	
	private String productName;
	
	@Min(value=0,message="Product Price can't be less than zero")
	private int price;
	
	@NotBlank(message="Can't be blank!!!")
	private String productDescription;
	
	private LocalDate productExpiryDate;
	
	private String productCategory;
}
