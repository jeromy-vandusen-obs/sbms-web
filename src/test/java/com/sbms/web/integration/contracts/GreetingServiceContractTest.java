package com.sbms.web.integration.contracts;

import com.sbms.web.domain.Message;
import com.sbms.web.domain.repositories.MessageRepository;
import feign.FeignException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.startsWith;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = "com.sbms:sbms-greeting:+:stubs:8000", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class GreetingServiceContractTest {
    @Autowired
    private MessageRepository messageRepository;

    private Message englishMessage = new Message("en", "Hello World");
    private Message frenchMessage = new Message("fr", "Bonjour Monde");
    private Message portugueseMessage = new Message("pt", "Olá Mondo");
    private Message spanishMessage = new Message("es", "Hola Mundo");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getMessages_always_returnsListOfMessages() {
        List<Message> result = messageRepository.getMessages();

        assertThat(result).isNotNull().hasSize(3).containsExactlyInAnyOrder(englishMessage, frenchMessage, portugueseMessage);
    }

    @Test
    public void getMessage_whenMessageFound_returnsCorrectMessage() {
        Message result = messageRepository.getMessage("en");

        assertThat(result).isNotNull().isEqualToComparingFieldByField(englishMessage);
    }

    @Test
    public void getMessage_whenMessageNotFound_throwsException() {
        exception.expect(FeignException.class);
        exception.expectMessage(startsWith("status 404"));
        messageRepository.getMessage("ru");
    }

    @Test
    public void putMessage_whenNoMatchingMessageExists_returnsCreatedMessage() {
        Message result = messageRepository.putMessage("es", spanishMessage);

        assertThat(result).isEqualToComparingFieldByField(spanishMessage);
    }

    @Test
    public void putMessage_whenMatchingMessageExists_returnsUpdatedMessage() {
        Message updatedEnglishMessage = new Message(englishMessage.getLanguage(), "Hello World!");

        Message result = messageRepository.putMessage("en", updatedEnglishMessage);

        assertThat(result).isEqualToComparingFieldByField(updatedEnglishMessage);
    }

    @Test
    public void putMessage_whenLanguageDoesNotMatchPath_throwsException() {
        exception.expect(FeignException.class);
        exception.expectMessage(startsWith("status 400"));
        messageRepository.putMessage("ru", spanishMessage);
    }
}
