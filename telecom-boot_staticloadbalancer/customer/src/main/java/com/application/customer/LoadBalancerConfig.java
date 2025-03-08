package com.application.customer;

import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class LoadBalancerConfig {
	@Bean
	@Primary
	ServiceInstanceListSupplier serviceInstanceListSupplier() {
		return new DemoServiceInstanceListSuppler("FriendFamilyMS");
	}

}


class DemoServiceInstanceListSuppler implements ServiceInstanceListSupplier {

	private final String serviceId;

	DemoServiceInstanceListSuppler(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public Flux<List<ServiceInstance>> get() {
		
		return Flux.just(Arrays.asList(new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 9107, false),
				new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 9105, false)));

	}

	@Override
	public String getServiceId() {
		return serviceId;
	}
}

