package com.sbms.web.domain.repositories;

import com.sbms.web.domain.Health;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageActuatorRepositoryFallbackFactory implements FallbackFactory<MessageActuatorRepository> {
    @Override
    public MessageActuatorRepository create(Throwable cause) {
        return () -> new Health("DOWN");
    }
}
