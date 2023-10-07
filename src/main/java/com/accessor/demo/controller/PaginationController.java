package com.accessor.demo.controller;

import java.security.Principal;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accessor.demo.common.dto.APIResponseDTO;
import com.accessor.demo.common.dto.TrackYourCityDTO;
import com.accessor.demo.common.exception.ApplicationSystemException;
import com.accessor.demo.common.exception.CustomException;
import com.accessor.demo.common.exception.GlobalException;
import com.accessor.demo.common.service.CityService;
import com.accessor.demo.common.util.CommonConstants;
import com.accessor.demo.common.vo.CityProjectVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class PaginationController {
	
@Autowired
CityService cityService;
	
private final Logger log = LoggerFactory.getLogger(PaginationController.class);
	
	private static final String EMPLOYEE_PAGE_DETAILS = "/employee-page-details";
	
	@ApiOperation(value = "Employee Page Detail", notes = "TO list the projects employee page.", response = APIResponseDTO.class, produces = "application/json")
	@PostMapping(value = EMPLOYEE_PAGE_DETAILS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APIResponseDTO> activeProject(@RequestBody CityProjectVO activeProjectVO,
			@RequestHeader(value = "X-UserId") Integer userId, Principal principal) throws ApplicationSystemException {
			Date entryTime = new Date();
		   log.info("Entering in to the active project list method--------> "+entryTime);
		APIResponseDTO response = new APIResponseDTO();
		String locale = activeProjectVO.getLocale();
		if(activeProjectVO.getLocale() == null ) {
			locale = CommonConstants.getAppProperty("default.locale");
		}
		TrackYourCityDTO trackDto = cityService.findInprogressCity(userId, activeProjectVO,locale);
		if (trackDto != null) {
			response.setBody(trackDto);
			response.setMessage(CommonConstants.RESPONSE_MSG_SUCCESS);
			response.setStatus(HttpStatus.OK.value());
		} else {
			throw new CustomException(CommonConstants.RECORD_NOT_FOUND_MESSAGE, HttpStatus.NO_CONTENT.value());
		}
		Date exitTime = new Date();
		log.info("Exiting in to the active project list method--------->" + exitTime);
		log.info("Total Time taken for active project list method : "+Seconds.secondsBetween(new DateTime(entryTime),new DateTime(exitTime)).getSeconds() % 60 +" Seconds");
		return new ResponseEntity<APIResponseDTO>(response, HttpStatus.OK);
	}

}
