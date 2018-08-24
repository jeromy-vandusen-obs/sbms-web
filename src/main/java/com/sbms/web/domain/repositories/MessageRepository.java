package com.sbms.web.domain.repositories;

import com.sbms.web.domain.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "http://sbms-greeting", fallback = MessageRepositoryFallback.class)
public interface MessageRepository {
    @GetMapping("/messages")
    List<Message> getMessages();

    @GetMapping("/messages/{language}")
    Message getMessage(@PathVariable("language") String language);

    @PostMapping(value = "/messages", consumes = MediaType.APPLICATION_JSON_VALUE)
    Message createMessage(@RequestBody Message message);
}
