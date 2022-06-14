/**
 * 
 */
package com.techyShubham.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author shubh
 *
 */
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
//		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	/**
	 * 
	 */
	public ResourceNotFoundException() {
		// TODO Auto-generated constructor stub
	}

}
