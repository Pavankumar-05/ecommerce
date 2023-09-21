package com.dnb.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exceptions.ProductIdNotFoundException;
import com.dnb.ecommerce.exceptions.UniqueProductNameExpection;
import com.dnb.ecommerce.repo.ProductRepository;

/*
 * This is the ProductServiceImpl class implements ProductService interface
 */
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	
	/*
	 * creates(saves) the new Product details to the database
	 * @param product of type Product 
	 * @return The creates(Saved) product
	 */
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub

		return productRepository.save(product);
	}
	
	/*
	 *Gets All the Products present in the database 
	 *@return List of products present in the database
	 */
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}

	

	
/*
 * Checks the database whether there is any product with the given productId
 * @param productId The Unique ID of the product to check whether it exists or not
 * @return boolean value based on productId availability
 */
	@Override
	public boolean checkExistsById(String productId) {
		// TODO Auto-generated method stub
		if(productRepository.existsById(productId))
			return true;
		else
			return false;
	}

	/*
	 * Deletes the Product if productId exists 
	 * @param productId The Unique ID of the product to check whether it exists or not
	 * @return boolean value which indicates whether the product has been deleted or not
	 * @throws ProductIdNotFoundException if the product is not found
	 */
	@Override
	public boolean deleteProductById(String productId) throws ProductIdNotFoundException {
		boolean isPresent = productRepository.existsById(productId);
		if(isPresent) {
			productRepository.deleteById(productId);
			
		}else {
			throw new ProductIdNotFoundException("Id not Found");
		}
		if(productRepository.existsById(productId))
			return false;
		else
			return true;
	}

	/*
	 * Retrieves the product based on productId
	 * @param productId The Unique ID of the product to retrieve
	 * @return An optional containing the product if found, or empty if not found. 
	 * @throws ProductIdNotFoundException if the product is not found
	 */
	@Override
	public Optional<Product> getProductById(String productId) throws ProductIdNotFoundException {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	
	/*
	 * Updates a product based on its unique ID
	 * @param productId The Unique ID of the product to update
	 * @return The updated product.
	 * @throws ProductIdNotFoundException if the product is not found, UniqueProductNameExpection if the product name is not unique
	 */
	@Override
	public Product updateProduct(String productId, Product updatedProduct) throws UniqueProductNameExpection, ProductIdNotFoundException {
		
		Product existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ProductIdNotFoundException("Product not found with given ID"));
		
		
		if(updatedProduct.getProductName()!=null && !updatedProduct.getProductName().equals(existingProduct.getProductName())) {
			
			if(productRepository.findByProductName(updatedProduct.getProductName()).isPresent()) {
				throw new UniqueProductNameExpection("Product Name must be unique");
			}
			existingProduct.setProductName(updatedProduct.getProductName());
		}
		
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setProductDescription(updatedProduct.getProductDescription());
		
		return productRepository.save(existingProduct);
		
	}
	
	/*
	 * Checks if a product with the given product name is unique in the database.
	 * @param productName The product name to check for uniqueness.
	 * @return true if no product with the same name exists , false otherwise.
	 */
	@Override
	public boolean isProductNameUnique(String productName) {
		// TODO Auto-generated method stub
		return !productRepository.findByProductName(productName).isPresent();
	}


	
	
}
