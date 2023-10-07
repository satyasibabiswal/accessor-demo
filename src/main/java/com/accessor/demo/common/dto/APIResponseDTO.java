package com.accessor.demo.common.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.accessor.demo.common.util.CommonConstants;

public class APIResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	private Object body;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	public APIResponseDTO() {
	}
	
	/**
		this.setMessage(CommonConstants.ResponseMsg.SUCCESS.toString());
		this.setStatus(CommonConstants.RESPONSE_CODE_SUCCESS);
	 */
	public void setSuccess() {
		this.setMessage(CommonConstants.RESPONSE_MSG_SUCCESS);
		this.setStatus(CommonConstants.RESPONSE_CODE_SUCCESS);
	}
	
	/**
		this.setMessage(CommonConstants.ResponseMsg.FAILED.toString());
		this.setStatus(CommonConstants.RESPONSE_CODE_FAILED);
	 */
	public void setFail() {
		this.setMessage(CommonConstants.RESPONSE_MSG_FAILED);
		this.setStatus(CommonConstants.RESPONSE_CODE_FAILED);
	}
	
	public void setExpectationFailed() {
		this.setMessage(CommonConstants.RESPONSE_MSG_FAILED);
		this.setStatus(HttpStatus.EXPECTATION_FAILED.value());
	}
	
	public void setForbiddenMessage(){
		this.setStatus(403);
		this.setMessage(CommonConstants.FORBIDDEN_MESSAGE);
	}
}
