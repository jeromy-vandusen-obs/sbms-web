package com.sbms.web.integration.pages;

import com.sbms.web.domain.Message;
import com.sbms.web.domain.repositories.MessageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomePageTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageRepository messageRepository;

    @Test
    public void homePage_always_displaysCorrectly() throws Exception {
        Message englishMessage = new Message("en", "Hello World");
        Message frenchMessage = new Message("fr", "Bonjour Monde");
        Message portugueseMessage = new Message("pt", "Olá Mondo");
        when(messageRepository.getMessages()).thenReturn(Arrays.asList(englishMessage, frenchMessage, portugueseMessage));
        when(messageRepository.getMessage("en")).thenReturn(englishMessage);

        Document html = Jsoup.parse(mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString());

        assertThat(html.title()).isEqualTo("Spring Boot Microservices - Welcome!");
        Element body = html.body();
        assertThat(body.getElementsByTag("h1").first().text()).isEqualTo("Spring Boot Microservices - Demo Application");
        assertThat(body.getElementsByClass("alert-primary").first().text()).contains("Hello World");
    }
}
