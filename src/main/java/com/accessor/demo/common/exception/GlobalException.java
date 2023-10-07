/**
 * 
 */
package com.accessor.demo.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author raghuveera.r
 *
 */
public class GlobalException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	
	public GlobalException(String exception,HttpStatus errorCode) {
		super(exception);
		this.httpStatus=errorCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
