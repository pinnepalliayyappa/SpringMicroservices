package com.application.customer.service;

import com.application.customer.dto.PlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerSynchronous {
    @Autowired
    RestTemplate restTemplate;

    public PlanDTO getPlanforid(int planid){
        return restTemplate.getForObject("http://PlanMS/plans/"+planid, PlanDTO.class);
    }
    @SuppressWarnings("unchecked")
    public List<Long> getfamilyobject(Long phoneNo){
        return restTemplate.getForObject("http://FriendFamilyMS/customers/"+phoneNo+"/friends", List.class);
    }
}
