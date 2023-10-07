package com.accessor.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.WebApplicationContext;



@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.accessor.demo")
@EnableScheduling
public class AccessorDemoApplication extends SpringBootServletInitializer {

	@Autowired
	public ConfigurableApplicationContext configurableAppContext;
	
	@Autowired
	public WebApplicationContext webApplicationContext;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AccessorDemoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(AccessorDemoApplication.class, args);
	}

}
