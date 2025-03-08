package com.application.plan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.application.plan.dto.PlanDTO;
import com.application.plan.entity.Plan;
import com.application.plan.repository.PlanRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlanService {
	private static final Log LOGGER = LogFactory.getLog(PlanService.class);

	@Autowired
	PlanRepository planRepo;

	// Fetches all plan details
	public List<PlanDTO> getAllPlans() {
		List<Plan> plans = planRepo.findAll();
		List<PlanDTO> planDTOs = new ArrayList<>();

		for (Plan plan : plans) {
			PlanDTO planDTO = PlanDTO.valueOf(plan);
			planDTOs.add(planDTO);
		}

		LOGGER.info("Plan details : "+ planDTOs);
		return planDTOs;
	}

	// To get a plan based on plan Id
	public PlanDTO getPlan(Integer planId) {
		PlanDTO planDTO = null;
		Optional<Plan> optPlan = planRepo.findById(planId);
		if (optPlan.isPresent()) {
			Plan plan = optPlan.get();
			planDTO = PlanDTO.valueOf(plan);
		}

		return planDTO;
	}

}
