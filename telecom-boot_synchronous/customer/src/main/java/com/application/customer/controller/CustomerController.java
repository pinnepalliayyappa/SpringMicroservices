package com.application.customer.controller;

import com.application.customer.dto.CustomerDTO;
import com.application.customer.dto.LoginDTO;
import com.application.customer.dto.PlanDTO;
import com.application.customer.service.CustomerService;
import com.application.customer.service.CustomerSynchronous;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {
    private static final Log LOGGER = LogFactory.getLog(CustomerController.class);
    @Autowired
    CustomerService custService;
    String planUrl;
    String friendFamilyUrl;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CustomerSynchronous customerSynchronous;

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
    @CircuitBreaker(name = "customerService", fallbackMethod = "getCustomerProfileFallback")
    public CustomerDTO getCustomerProfile(@PathVariable("phoneNo") Long phoneNo) {
        LOGGER.info("Profile request for customer " + phoneNo);
        CustomerDTO custdto = this.custService.getCustomerProfile(phoneNo);
        LOGGER.info("Profile request for customer " + planUrl);
        LOGGER.info("Profile request for customer " + friendFamilyUrl);
//        List<ServiceInstance> services = discoveryClient.getInstances("PlanMS");
//        if(services!=null && !services.isEmpty())
//            planUrl = services.get(0).getUri().toString();
        long planStart = System.currentTimeMillis();
        PlanDTO planDTO = customerSynchronous.getPlanforid(custdto.getCurrentPlan().getPlanId());
        long planend = System.currentTimeMillis();
        custdto.setCurrentPlan(planDTO);
//        List<ServiceInstance> ffservices = discoveryClient.getInstances("FriendFamilyMS");
//        if(ffservices!=null && !ffservices.isEmpty())
//            friendFamilyUrl = ffservices.get(0).getUri().toString();
        long friendStart = System.currentTimeMillis();
        @SuppressWarnings("unchecked")
        List<Long> friends = customerSynchronous.getfamilyobject(phoneNo);
        long friendend = System.currentTimeMillis();
        LOGGER.info("PlanTime"+(planend-planStart));
        LOGGER.info("FamilyTime"+(friendend-friendStart));
        LOGGER.info("OverallTime"+(friendend-planStart));
        custdto.setFriendAndFamily(friends);
        return custdto;
    }
    public CustomerDTO getCustomerProfileFallback(Long phoneNo, Throwable throwable) {
        LOGGER.info("============ In Fallback =============");

        return new CustomerDTO();
    }
}
