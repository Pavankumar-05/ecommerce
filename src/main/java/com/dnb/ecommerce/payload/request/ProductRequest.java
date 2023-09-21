package com.dnb.ecommerce.payload.request;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/*
 * The ProductRequest class represents a request object used for creating or updating product information.
 */
@Data
public class ProductRequest {
	@NotBlank(message="Product Name can't be blank!!!")
	@Column(nullable=false,unique=true)
	private String productName;
	@Min(value=0,message="Product Price can't be less than zero")
	private int price;
	@NotBlank(message="Can't be blank!!!")
	private String productDescription;
	private LocalDate productExpiryDate;
	private String productCategory;
	
}
