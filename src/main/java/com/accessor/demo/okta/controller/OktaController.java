package com.accessor.demo.okta.controller;

import java.security.Principal;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OktaController {

	@GetMapping("/login")
	public String welcomeToUser(Principal principal) {
		
	return "Hi"+principal.getName()+"Welcome to User";	
	}
}
