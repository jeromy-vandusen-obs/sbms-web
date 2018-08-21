package com.sbms.web.domain.repositories;

import com.sbms.web.domain.Message;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageRepositoryFallback implements MessageRepository {
    @Override
    public List<Message> getMessages() {
        return Arrays.asList(
                new Message("en", "Error")
        );
    }

    @Override
    public Message getMessage(String language) {
        return new Message(language, "Error");
    }
}
