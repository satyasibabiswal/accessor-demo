package com.accessor.demo.controller;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Produces;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accessor.demo.common.dto.APIResponseDTO;
import com.accessor.demo.common.dto.MasterDataDTO;
import com.accessor.demo.common.exception.GlobalException;
import com.accessor.demo.common.util.CommonConstants;
import com.accessor.demo.service.CityDetailService;
import com.accessor.demo.service.CityReportServie;

import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api")
public class CityController {
	
	private final Logger log = LoggerFactory.getLogger(CityController.class);
	
	private static final String EMPLOYEE_DETAILS = "/employee-details";

	@Autowired
	private CityDetailService cityDetailService;
	@Autowired
	private CityReportServie cityReportServie;

	@ApiOperation(value = "CityDetails", notes = "To list all the  specify master data message", response = List.class, produces = "application/json")
	@GetMapping(EMPLOYEE_DETAILS)
	@Produces(value = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<APIResponseDTO> getMasterDataSpecify(Principal principal) {
		Date entryTime = new Date();
		log.info("Entering the create method--------> "+entryTime);
		APIResponseDTO response = new APIResponseDTO();
		MasterDataDTO masterDataDTO = new MasterDataDTO();
		try {
			masterDataDTO = cityDetailService.getCityDetail();
		} catch (com.accessor.demo.common.exception.ApplicationSystemException e) {
			log.error("error", e);
			throw new GlobalException(e.getMessage(), e.getHttpStatus());
		}
		response.setMessage(CommonConstants.RESPONSE_MSG_SUCCESS);
		response.setBody(masterDataDTO);
		response.setStatus(HttpStatus.OK.value());
		Date exitTime = new Date();
		log.info("Exiting in to the active project list method--------->" + exitTime);
		log.info("Total Time taken for active project list method : "+Seconds.secondsBetween(new DateTime(entryTime),new DateTime(exitTime)).getSeconds() % 60 +" Seconds");
		return new ResponseEntity<APIResponseDTO>(response, HttpStatus.OK);
	}
  
	@ApiOperation(value = "CityDetails", notes = "To list all the  specify master data message", response = List.class, produces = "application/json")
	@GetMapping("report/{format}")
	public String generateCityReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return cityReportServie.exportReport(format);
		
		
	}
	
}
