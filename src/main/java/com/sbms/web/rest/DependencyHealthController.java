package com.sbms.web.rest;

import com.sbms.web.domain.repositories.MessageActuatorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DependencyHealthController {
    private final MessageActuatorRepository messageActuatorRepository;

    public DependencyHealthController(MessageActuatorRepository messageActuatorRepository) {
        this.messageActuatorRepository = messageActuatorRepository;
    }

    @GetMapping("/dependencyHealth")
    public DependencyHealth dependencyHealth() {
        return new DependencyHealth(messageActuatorRepository.getHealth().getStatus());
    }
}
