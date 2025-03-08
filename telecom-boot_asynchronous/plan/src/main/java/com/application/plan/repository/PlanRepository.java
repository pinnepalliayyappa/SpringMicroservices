package com.application.plan.repository;


import com.application.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlanRepository extends JpaRepository<Plan, Integer> {
	


}
