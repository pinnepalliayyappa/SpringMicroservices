package com.application.friendfamily.controller;

import com.application.friendfamily.entity.FriendFamily;
import com.application.friendfamily.repository.FriendFamilyRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.application.friendfamily.service.FriendFamilyService;
import com.application.friendfamily.dto.FriendFamilyDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FriendFamilyController {

	private static final Log LOGGER = LogFactory.getLog(FriendFamilyController.class);

	@Autowired
	FriendFamilyService friendService;
	@Autowired
	FriendFamilyRepository friendRepo;

	// Create Friend Family
	@PostMapping(value = "/customers/{phoneNo}/friends", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveFriend(@PathVariable("phoneNo") Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		LOGGER.info("Creation request for customer "+phoneNo+" with data "+ friendDTO);
		friendService.saveFriend(phoneNo, friendDTO);
	}
	@GetMapping(value = "/customers/{phoneNo}/friends")
	public List<Long> saveFriend(@PathVariable("phoneNo") Long phoneNo) {
		LOGGER.info("Creation request for customer "+phoneNo+" with data ");
		List<FriendFamily> frienandfamily = friendRepo.findByPhoneNo(phoneNo);
		List<Long> friends = new ArrayList();
		friends = frienandfamily.stream().map(FriendFamily::getPhoneNo).collect(Collectors.toList());
		return friends;
	}

}
