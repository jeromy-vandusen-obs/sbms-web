package com.sbms.web.ui;

import com.sbms.web.domain.Message;
import com.sbms.web.domain.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {
    private final MessageRepository messageRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    public HomeController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @ModelAttribute("applicationName")
    public String applicationName() {
        return applicationName;
    }

    @ModelAttribute("buildVersion")
    public String buildVersion() {
        return buildVersion;
    }

    @ModelAttribute("newMessage")
    public Message newMessage() {
        return new Message();
    }

    @ModelAttribute("customMessage")
    public String customMessage(Locale locale) {
        try {
            return messageRepository.getMessage(locale.getLanguage()).getContent();
        }
        catch (Throwable t) {
            System.out.println("***** " + t.getClass().getName());
            throw t;
        }
    }

    @ModelAttribute("messages")
    public List<Message> messages() {
        return messageRepository.getMessages();
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/")
    public String createMessage(@Valid @ModelAttribute("newMessage") Message newMessage, BindingResult result) {
        if (result.hasErrors()) {
            return "home";
        }
        messageRepository.putMessage(newMessage.getLanguage(), newMessage);
        return "redirect:/";
    }

    @PostMapping("/deleteMessage/{language}")
    public String deleteMessage(@PathVariable String language) {
        messageRepository.deleteMessage(language);
        return "redirect:/";
    }
}
