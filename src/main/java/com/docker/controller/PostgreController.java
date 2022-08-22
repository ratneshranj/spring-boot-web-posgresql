package com.docker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostgreController {
	
	private static final Logger logger = LogManager.getLogger(PostgreController.class);

	private static final String SUCCESS = "success";
	
	@RequestMapping(value = "/driverStatus")
	public String getDriverStatus() {
		logger.debug("------------getDriverStatus--------------");
		return SUCCESS;
	}

}
