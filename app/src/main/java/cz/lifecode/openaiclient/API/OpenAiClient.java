package cz.lifecode.openaiclient.API;

import java.io.IOException;
import java.net.HttpURLConnection;

import cz.lifecode.openaiclient.API.Exceptions.ClientOpenAiException;
import cz.lifecode.openaiclient.API.Exceptions.LimitationOpenAiException;
import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;
import cz.lifecode.openaiclient.API.Exceptions.ServerOpenAiException;
import cz.lifecode.openaiclient.API.Exceptions.ServerOverloadedOpenAiException;
import cz.lifecode.openaiclient.API.Exceptions.UnauthorizedOpenAiException;
import cz.lifecode.openaiclient.API.Stream.JsonInputStreamReader;

public class OpenAiClient {
    Authorization authorization;

    OpenAiClient(String openAiApiKey) {
        authorization = new Authorization(openAiApiKey);
    }

    public static void handleConnection(HttpURLConnection connection) throws OpenAiException {
        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new ClientOpenAiException(e);
        }

        if (responseCode > 299) {
            String error = new JsonInputStreamReader(connection.getErrorStream()).readAll();
            if (responseCode == 401) {
                throw new UnauthorizedOpenAiException(error);
            } else if (responseCode == 429) {
                throw new LimitationOpenAiException(error);
            } else if (responseCode == 500) {
                throw new ServerOpenAiException(error);
            } else if (responseCode == 503) {
                throw new ServerOverloadedOpenAiException(error);
            } else {
                throw new ClientOpenAiException(error);
            }
        }
    }
}
