package cz.lifecode.openaiclient;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.Model.ChatModel;

public class LoadChatModelsUnitTest {
    @Test
    public void fetch_isCorrect() {
        String apiKey = Configuration.getInstance().getOpenAiApiKey();
        ChatModel chatModel = new ChatModel(new Authorization(apiKey));
        List<String> modelIds = chatModel.getAvailableModelIds();
        assertTrue(modelIds.size() > 0);
    }
}
