package com.dnb.ecommerce.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dnb.ecommerce.exceptions.ProductIdNotFoundException;
import com.dnb.ecommerce.exceptions.UniqueProductNameExpection;

/*
 * The AppAdvice class is a global controller advice class that handles exceptions and provides advice for the entire application.
 */
@ControllerAdvice
public class AppAdvice {

	/*
	 * Handles exceptions of type ProductIdNotFoundException and returns a custom error response.
	 * @param e The ProductIdNotFoundException to handle.
	 * @return A ResponseEntity with a custom error response.
	 */
	@ExceptionHandler(ProductIdNotFoundException.class)
	public ResponseEntity<?> ProductIdNotFoundExceptionHandler(ProductIdNotFoundException e){
		Map<String,String> map = new HashMap<>();
		map.put("Message", e.getMessage());
		map.put("HttpStatus", HttpStatus.NOT_FOUND+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	/*
	 * Handles exceptions of type UniqueProductNameExpection and returns a custom error response.
	 * @param e The UniqueProductNameExpection to handle.
	 * @return A ResponseEntity with a custom error response.
	 */
	@ExceptionHandler(UniqueProductNameExpection.class)
	public ResponseEntity<?> UniqueProductNameExpectionHandler(UniqueProductNameExpection e){
		Map<String,String> map = new HashMap<>();
		map.put("Message", e.getMessage());
		map.put("HttpStatus", HttpStatus.CONFLICT+"");
		return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}
	
	
	
}
