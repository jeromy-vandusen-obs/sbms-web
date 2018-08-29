package com.sbms.web.domain.repositories;

import com.sbms.web.domain.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageRepositoryFallback implements MessageRepository {
    private static final List<Message> messages = new ArrayList<>();
    static {
        messages.add(new Message("??", "Error"));
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public Message getMessage(String language) {
        return messages.get(0);
    }

    @Override
    public Message putMessage(String language, Message message) {
        return message;
    }

    @Override
    public void deleteMessage(String language) {
    }
}
