package com.application.customer.service;

import com.application.customer.dto.PlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class CustomerSynchronous {
    @Autowired
    RestTemplate restTemplate;

    public CompletableFuture<PlanDTO> getPlanforid(int planid){
        return CompletableFuture.supplyAsync(()->restTemplate.getForObject("http://PlanMS/plans/"+planid, PlanDTO.class));
    }
    @SuppressWarnings("unchecked")
    public CompletableFuture<List<Long>> getfamilyobject(Long phoneNo){
        return CompletableFuture.supplyAsync(()->restTemplate.getForObject("http://FriendFamilyMS/customers/"+phoneNo+"/friends", List.class));
    }
}
