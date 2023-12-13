package cz.lifecode.openaiclient;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;
import cz.lifecode.openaiclient.API.Model.ChatModel;

public class LoadChatModelsUnitTest {
    @Test
    public void fetch_isCorrect() {
        String apiKey = Configuration.getInstance().getOpenAiApiKey();
        ChatModel chatModel = new ChatModel(new Authorization(apiKey));
        List<String> modelIds;
        try {
            modelIds = chatModel.getAvailableModelIds();
        } catch (OpenAiException e) {
            Logger.getGlobal().warning(e.getMessage());
            return;
        }

        assertTrue(modelIds.size() > 0);
    }
}
