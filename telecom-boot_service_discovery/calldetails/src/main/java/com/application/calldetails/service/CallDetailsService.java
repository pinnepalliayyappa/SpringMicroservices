package com.application.calldetails.service;

import java.util.ArrayList;
import java.util.List;

import com.application.calldetails.dto.CallDetailsDTO;
import com.application.calldetails.entity.CallDetails;
import com.application.calldetails.repository.CallDetailsRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class CallDetailsService {
	Log logger = LogFactory.getLog(CallDetailsService.class);

	@Autowired
    CallDetailsRepository callDetailsRepo;

	// Fetches call details of a specific customer
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {

		logger.info("Calldetails request for customer "+ phoneNo);

		List<CallDetails> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
		List<CallDetailsDTO> callsDTO = new ArrayList<>();

		for (CallDetails call : callDetails) {
			callsDTO.add(CallDetailsDTO.valueOf(call));
		}
		logger.info("Calldetails for customer : "+ callDetails);

		return callsDTO;
	}
}
