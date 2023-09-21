package com.dnb.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.ecommerce.dto.Product;

/*
 * The ProductRepository interface is responsible for managing Product entities in the database.
 * It extends the Spring Data JPA CrudRepository, providing basic CRUD operations
 */
@Repository
public interface ProductRepository extends  CrudRepository<Product, String>{
	
	/*
	 *Retrieves a product by its name.
	 *@param productName The name of the product to retrieve.
	 *@return An optional containing the product if found, or empty if not found. 
	 */
	Optional<Product> findByProductName(String productName);

	
}
