/**
 * 
 */
package com.accessor.demo.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author satyasiba
 *
 */
public class ApplicationSystemException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public ApplicationSystemException(String message,HttpStatus errorCode) {
		super(message);
		this.httpStatus=errorCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
