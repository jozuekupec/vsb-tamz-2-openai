package cz.lifecode.openaiclient.API;

import android.content.Context;
import android.content.SharedPreferences;

import cz.lifecode.openaiclient.API.Exceptions.InvalidAccessTokenOpenAiException;

public class Authorization {
    private final String openAiApiKey;

    public Authorization(Context context) throws InvalidAccessTokenOpenAiException {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        this.openAiApiKey = sharedPreferences.getString("apiToken", null);
        if (this.openAiApiKey == null) {
            throw new InvalidAccessTokenOpenAiException();
        }
    }

    public Authorization(String openAiApiKey) {
        this.openAiApiKey = openAiApiKey;
    }

    public String getAuthorizationHeaderValue() {
        return "Bearer " + openAiApiKey;
    }

    protected SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("open_ai", Context.MODE_PRIVATE);
    }
}
