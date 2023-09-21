package com.dnb.ecommerce.exceptions;


/*
 * The UniqueProductNameExpection class represents an exception that is thrown when attempting 
 * to create or update a product with a non-unique product name.
 */
public class UniqueProductNameExpection extends Exception {
	
public UniqueProductNameExpection(String msg) {
	
	super(msg);
}

@Override
	public String toString() {
		
		return this.getMessage();
	}

}
