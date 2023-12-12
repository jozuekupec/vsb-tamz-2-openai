package cz.lifecode.openaiclient.API;

public class Authorization {
    private final String openAiApiKey;

    public Authorization(String openAiApiKey) {
        this.openAiApiKey = openAiApiKey;
    }

    public String getAuthorizationHeaderValue() {
        return "Bearer " + openAiApiKey;
    }
}
