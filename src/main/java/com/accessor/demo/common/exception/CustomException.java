/**
 * 
 */
package com.accessor.demo.common.exception;

/**
 * @author satyasiba
 *
 */
public class CustomException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	public CustomException(String exception, Integer status) {
		super(exception);
		this.status=status;
	}
	public Integer getStatus() {
		return status;
	}
	

}
