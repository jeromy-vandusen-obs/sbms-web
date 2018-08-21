package com.sbms.web.ui;

import com.sbms.web.domain.Message;
import com.sbms.web.domain.repositories.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {
    private final MessageRepository messageRepository;

    public HomeController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @ModelAttribute("customMessage")
    public String customMessage(Locale locale) {
        return messageRepository.getMessage(locale.getLanguage()).getContent();
    }

    @ModelAttribute("messages")
    public List<Message> messages() {
        return messageRepository.getMessages();
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
