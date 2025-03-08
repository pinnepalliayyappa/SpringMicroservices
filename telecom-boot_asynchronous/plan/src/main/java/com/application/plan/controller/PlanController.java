package com.application.plan.controller;

import java.util.List;

import com.application.plan.dto.PlanDTO;
import com.application.plan.service.PlanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@CrossOrigin
public class PlanController {
	private static final Log LOGGER = LogFactory.getLog(PlanController.class);


	@Autowired
	PlanService planService;

	// Fetches all plan details
	@GetMapping(value = "/plans",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlanDTO> getAllPlans() {
		return planService.getAllPlans();
	}
	// To get a plan details based on plan id
	@GetMapping(value = "/plans/{planId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PlanDTO getPlan(@PathVariable("planId") Integer planId) {
		try{
			Thread.sleep(5000);
		}
		catch(Exception e) {
			LOGGER.info(e.getMessage());
		}
		return planService.getPlan(planId);
	}


}
