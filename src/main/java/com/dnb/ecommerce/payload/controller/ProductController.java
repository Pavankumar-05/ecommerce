package com.dnb.ecommerce.payload.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exceptions.ProductIdNotFoundException;
import com.dnb.ecommerce.exceptions.UniqueProductNameExpection;
import com.dnb.ecommerce.payload.request.ProductRequest;
import com.dnb.ecommerce.service.ProductService;
import com.dnb.ecommerce.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RequestToEntityMapper mapper;
	
	
	/*
	 * Create a new product listing.
	 * @param productRequest The request containing product information.
	 * @return A ResponseEntity with a success message and the created product details.
	 * @throws UniqueProductNameException if the product name is not unique
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest product) throws UniqueProductNameExpection{
		
		Product product1 = mapper.getProductEntityObject(product);
		
		Product product2;
		try {
			
			if(productService.isProductNameUnique(product1.getProductName())) {
				product2 = productService.createProduct(product1);
				return new ResponseEntity(product2, HttpStatus.CREATED);
			}
			else {
				throw new UniqueProductNameExpection("Product Name must be unique.");
			}
		
		} catch (ProductIdNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	/*
	 * Retrieves product details by providing its Unique ID.
	 * @param productId The unique ID of the product to retrieve.
	 * @return A ResponseEntity with the product details.
	 * @throws ProductIdNotFoundException if the product is not found
	 */
	@GetMapping("/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws ProductIdNotFoundException{
		Optional<Product> optional = productService.getProductById(productId);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}else {
			throw new ProductIdNotFoundException("Provide proper ID");
		}
	}
	
	/*
	 * Deletes a product by providing its unique ID.
	 * @param productId The unique ID of the product to delete.
	 * @return A ResponseEntity with a no content status
	 * @throws ProductIdNotFoundException if the product is not found
	 */
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws ProductIdNotFoundException{
		if(productService.checkExistsById(productId)) {
			productService.deleteProductById(productId);
			return ResponseEntity.noContent().build();
		}else {
			throw new ProductIdNotFoundException("Provide proper ID");
		}
	}
	
	/*
	 *Retrieves a list of all available products.
	 *@return A ResponseEntity with a list of product details
	 *@throws ProductIdNotFoundException if the product is not found
	 */
	@GetMapping("/allProducts")
	public ResponseEntity<?> getAllProducts() throws ProductIdNotFoundException{
		List<Product> list = productService.getAllProducts();
		if(list.size() > 0)
			return ResponseEntity.ok(list);
		else
			throw new ProductIdNotFoundException("No Details");
	}
	
	/*
	 * Updates product details based on its unique ID
	 * @param productId  The unique ID of the product to update.
	 * @return A ResponseEntity with the updated product details.
	 * @throws UniqueProductNameException if the product name is not unique
	 * @throws ProductIdNotFoundException if the product is not found
	 */
	@PutMapping("/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable String productId, @RequestBody Product updatedProduct) throws ProductIdNotFoundException, UniqueProductNameExpection{
		try {
			productService.updateProduct(productId, updatedProduct);
			return ResponseEntity.ok("Product updated successfully");
		}catch(UniqueProductNameExpection e) {
			throw new UniqueProductNameExpection("Product Name should be unique");
		}
	}
	
	
	
	

}
