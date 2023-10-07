package com.accessor.demo.common.service.impl;

import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.accessor.demo.common.service.MessageResolverService;

@Service
public class MessageResolverServiceImpl implements MessageResolverService{

	@Autowired
    private MessageSource messageSource;
	
	@Override
	public String getLocalizeMessage(String locale,String messageKey) {
		return messageSource.getMessage(messageKey, null,LocaleUtils.toLocale(locale));
	}

}