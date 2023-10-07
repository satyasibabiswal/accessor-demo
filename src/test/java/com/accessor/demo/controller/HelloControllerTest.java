package com.accessor.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accessor.demo.common.service.MessageResolverService;
import com.accessor.demo.service.HelloService;

@RunWith(SpringRunner.class)
public class HelloControllerTest {
	
private String version="/api";	
private static final String HELLO_DETAILS = "/hello-details";
private MockMvc mockMvc;

@InjectMocks
HelloController helloController=new HelloController();

@Mock
HelloService helloService;
@Mock
private MessageResolverService messageResolverService;

@Before
public void setUp() throws Exception{
	MockitoAnnotations.initMocks(this);
	this.mockMvc=MockMvcBuilders.standaloneSetup(helloController).build();
}

@Test
public void testHello() throws Exception{
	mockMvc.perform(get(version+HELLO_DETAILS)
			       .contentType(MediaType.APPLICATION_JSON_VALUE)
			       .accept(MediaType.APPLICATION_JSON_VALUE)
			       .param("projectId", "111")
			       .param("locale", "en"))
			       .andExpect(status().isOk()).andReturn();
 }
}
