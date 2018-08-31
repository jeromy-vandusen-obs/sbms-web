package com.sbms.web.domain.repositories;

import com.sbms.web.domain.Message;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageRepositoryFallbackFactory implements FallbackFactory<MessageRepository> {
    @Override
    public MessageRepository create(Throwable cause) {
        return new MessageRepository() {
            @Override
            public List<Message> getMessages() {
                return new ArrayList<>();
            }

            @Override
            public Message getMessage(String language) {
                return new Message(language, "-- Error --");
            }

            @Override
            public Message putMessage(String language, Message message) {
                return message;
            }

            @Override
            public void deleteMessage(String language) {
            }
        };
    }
}
