package com.transit.controller;

import java.net.URI;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.transit.beans.City;
import com.transit.service.DataLoader;
import com.transit.service.Transit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author nageshwarraokomandla;
 */
 

	@RestController
	@Api(value = "Application to check connectivity between two given cities exist or not")
	@ApiOperation(value = "Find if a path exists between two cities", notes = "Returns Yes if cites connected  otherwise no",  response = String.class)
	
	public class RoadtransitController {
		
		  private final Log LOGGER = LogFactory.getLog(getClass());
	
		  
		   @Autowired
		    DataLoader county;

		   @Autowired
		   Transit transit;

		  
		  
	@GetMapping (path="/connected",    produces = "application/json" )	
	public ResponseEntity<	String >getData(  @RequestParam String origin  , @RequestParam  String destination ) {
			
		
		  HttpHeaders responseHeaders = new HttpHeaders();
		  
		    City originCity = county.getCity(origin.toUpperCase());
	        City destCity = county.getCity(destination.toUpperCase());
	        Objects.requireNonNull(originCity);
	        Objects.requireNonNull(destCity);

		  

	        LOGGER.info("origin:-> " + origin + " destination:-> " + destination);

		   URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	              .build()
	              .toUri();
		   
		   String resp= transit.commute(originCity, destCity)? "Yes" : "No" ;
		   
		   responseHeaders.setLocation(location);
		   return new ResponseEntity<	String >(resp, responseHeaders, HttpStatus.OK);
		   
		
		}

	    @ExceptionHandler(NullPointerException.class)
	    public ResponseEntity<String> cityError() {
	    	 

			  HttpHeaders responseHeaders = new HttpHeaders();
			  URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		              .build()
		              .toUri();
			  responseHeaders.setLocation(location);
			
	    	 return new ResponseEntity<	String >("No", responseHeaders, HttpStatus.OK);
	    }

	
}
