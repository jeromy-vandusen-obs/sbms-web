package com.sbms.web.domain.repositories;

import com.sbms.web.domain.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "sbms-greeting", fallbackFactory = MessageRepositoryFallbackFactory.class)
public interface MessageRepository {
    @GetMapping("/v1/messages")
    List<Message> getMessages();

    @GetMapping("/v1/messages/{language}")
    Message getMessage(@PathVariable("language") String language);

    @PutMapping(value = "/v1/messages/{language}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Message putMessage(@PathVariable("language") String language, @RequestBody Message message);

    @DeleteMapping(value = "/v1/messages/{language}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void deleteMessage(@PathVariable("language") String language);
}
