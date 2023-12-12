package cz.lifecode.openaiclient.API;

public class OpenAiClient {
    Authorization authorization;

    OpenAiClient(String openAiApiKey) {
        authorization = new Authorization(openAiApiKey);
    }
}
