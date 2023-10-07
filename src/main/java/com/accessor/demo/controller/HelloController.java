package com.accessor.demo.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.accessor.demo.common.dto.APIResponseDTO;
import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.common.exception.GlobalException;
import com.accessor.demo.common.service.MessageResolverService;
import com.accessor.demo.common.util.CommonConstants;
import com.accessor.demo.service.HelloService;
import com.accessor.demo.common.exception.ApplicationSystemException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class HelloController {
	@Autowired
	private MessageResolverService messageResolverService;
	@Autowired 
	HelloService helloService;
    private final Logger log = LoggerFactory.getLogger(HelloController.class);
	private static final String HELLO_DETAILS = "/hello-details";
	
	@ApiOperation(value = "HelloDetails", notes = "To list the hello message", response = List.class, produces = "application/json")
	@GetMapping(value = HELLO_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APIResponseDTO> hello(@RequestParam("projectId") Integer projectId,
			@RequestParam(value="locale",required = true)String locale) {
		
		Date entryTime = new Date();
		 log.info("Entering in to the Detailed method--------> "+entryTime);
		
		APIResponseDTO response = new APIResponseDTO();
		try {
			if(locale == null) {
				locale = CommonConstants.getAppProperty("default.locale");
			}
			Integer helloId=helloService.getHelloDetail(projectId);
			if(helloId !=null) {
				response.setMessage(messageResolverService.getLocalizeMessage(locale, CommonConstants.TASK_SKIP_SUCESS_MSG));
				response.setStatus(HttpStatus.OK.value());
			 }else {
				 response.setMessage(messageResolverService.getLocalizeMessage(locale, CommonConstants.APPLICATION_ERROR_MSG));
					response.setStatus(HttpStatus.NO_CONTENT.value());
			   }
		    } catch (ApplicationSystemException exception) {
				log.error(getClass().getName() + ".projectDetailedView()", exception);
				throw new GlobalException(messageResolverService.getLocalizeMessage(locale,CommonConstants.APPLICATION_ERROR_MSG), exception.getHttpStatus());
			}
		
		return new ResponseEntity<APIResponseDTO>(response, HttpStatus.OK);
      }
}
