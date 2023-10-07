package com.accessor.demo.service;

import com.accessor.demo.common.exception.ApplicationSystemException;

public interface HelloService {

	Integer getHelloDetail(Integer projectId) throws ApplicationSystemException;

}
