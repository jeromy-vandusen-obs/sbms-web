package com.sbms.web.domain.repositories;

import com.sbms.web.domain.Health;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sbms-greeting", fallbackFactory = MessageActuatorRepositoryFallbackFactory.class)
public interface MessageActuatorRepository {
    @GetMapping("/actuator/health")
    Health getHealth();
}
