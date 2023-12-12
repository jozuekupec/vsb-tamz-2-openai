package cz.lifecode.openaiclient;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.Chat.ChatCompletion;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatRequestDTO;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatResponseDTO;
import cz.lifecode.openaiclient.API.DTO.Chat.MessageDTO;

public class ChatCompletionUnitTest {
    @Test
    public void fetch_isCorrect() {
        String apiKey = Configuration.getInstance().getOpenAiApiKey();
        ChatCompletion chatCompletion = new ChatCompletion(new Authorization(apiKey));

        MessageDTO message = new MessageDTO();
        message.setRole("user");
        message.setContent("What is the recommended amount of pancakes for little person such as Kevin from the Home alone?");
        MessageDTO[] messages = {message};
        ChatRequestDTO chatRequest = new ChatRequestDTO();
        chatRequest.setModel("gpt-4");
        chatRequest.setMessages(messages);

        ChatResponseDTO response = chatCompletion.sendMessage(chatRequest);
        assertTrue(response.getModel().contains(chatRequest.getModel()));
        assertTrue(response.getChoices().length > 0);
    }
}
