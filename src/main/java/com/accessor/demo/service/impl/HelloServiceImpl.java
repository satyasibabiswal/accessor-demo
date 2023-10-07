package com.accessor.demo.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.common.exception.GlobalException;
import com.accessor.demo.common.service.MessageResolverService;
import com.accessor.demo.common.service.impl.MessageResolverServiceImpl;
import com.accessor.demo.common.util.CommonConstants;
import com.accessor.demo.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{
	@Override
	public Integer getHelloDetail(Integer projectId) throws ApplicationSystemException {
		HttpStatus errorCode = null;
		String message;
		if(projectId<10) {
			return projectId;
		}
		else {
			message="Bad applicatioon";
			throw new ApplicationSystemException(message, errorCode);
		}
	
	}

}
