package cz.lifecode.openaiclient.API;

public class Authorization {
    private final String openAiApiToken;
    public final static String SHARED_PREFERENCES_REPOSITORY = "openAi";
    public final static String API_TOKEN_SHARED_PREFERENCES_KEY = "apiToken";

    public Authorization(String openAiApiToken) {
        this.openAiApiToken = openAiApiToken;
    }

    public String getAuthorizationHeaderValue() {
        return "Bearer " + openAiApiToken;
    }
}
