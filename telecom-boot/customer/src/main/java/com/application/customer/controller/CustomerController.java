package com.application.customer.controller;

import com.application.customer.dto.CustomerDTO;
import com.application.customer.dto.LoginDTO;
import com.application.customer.service.CustomerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CustomerController {
    private static final Log LOGGER = LogFactory.getLog(CustomerController.class);
    @Autowired
    CustomerService custService;

    public CustomerController() {
    }

    @PostMapping(
            value = {"/customers"},
            consumes = {"application/json"}
    )
    public void createCustomer(@RequestBody CustomerDTO custDTO) {
        LOGGER.info("Creation request for customer " + custDTO);
        this.custService.createCustomer(custDTO);
    }

    @PostMapping(
            value = {"/login"},
            consumes = {"application/json"}
    )
    public boolean login(@RequestBody LoginDTO loginDTO) {
        LOGGER.info("Login request for customer " + loginDTO.getPhoneNo() + " with password " + loginDTO.getPassword());
        return this.custService.login(loginDTO);
    }

    @GetMapping(
            value = {"/customers/{phoneNo}"},
            produces = {"application/json"}
    )
    public CustomerDTO getCustomerProfile(@PathVariable("phoneNo") Long phoneNo) {
        LOGGER.info("Profile request for customer " + phoneNo);
        return this.custService.getCustomerProfile(phoneNo);
    }
}
