package com.dnb.ecommerce.exceptions;

/*
 * The ProductIdNotFoundException is thrown when a product with a specific ID is not found in the system.
 * This exception is typically used when attempting to retrieve or manipulate a product with an ID that doesn't exist.
 */
public class ProductIdNotFoundException extends Exception {
	public ProductIdNotFoundException(String msg) {
		super(msg);
	}
	@Override
	public String toString() {
		return this.getMessage();
	}

}
