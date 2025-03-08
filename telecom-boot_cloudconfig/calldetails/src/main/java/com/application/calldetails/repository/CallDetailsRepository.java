package com.application.calldetails.repository;

import java.util.List;

import com.application.calldetails.entity.CallDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CallDetailsRepository extends JpaRepository<CallDetails, Long> {

	List<CallDetails> findByCalledBy(long calledBy);
}
