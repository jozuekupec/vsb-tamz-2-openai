package cz.lifecode.openaiclient.API.Chat;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.Exceptions.ClientOpenAiException;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatRequestDTO;
import cz.lifecode.openaiclient.API.DTO.Chat.ChatResponseDTO;
import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;
import cz.lifecode.openaiclient.API.OpenAiClient;
import cz.lifecode.openaiclient.API.Stream.JsonInputStreamReader;

public class ChatCompletion {
    protected static final String CREATE_COMPLETION_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    protected static final String CREATE_COMPLETION_METHOD = "POST";
    private final Authorization authorization;

    public ChatCompletion(Authorization authorization) {
        this.authorization = authorization;
    }

    public ChatResponseDTO sendMessage(ChatRequestDTO chatRequest) throws OpenAiException {
        HttpURLConnection connection = null;
        ChatResponseDTO chatResponseDTO = null;

        try {
            URL url = new URL(CREATE_COMPLETION_ENDPOINT);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(CREATE_COMPLETION_METHOD);
            connection.setRequestProperty("Authorization", authorization.getAuthorizationHeaderValue());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(15000); // Timeout nastaven na 15s
            connection.setReadTimeout(15000);
            connection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            outputStreamWriter.write(new Gson().toJson(chatRequest));
            outputStreamWriter.flush();

            connection.connect();
            OpenAiClient.handleConnection(connection);

            String response = new JsonInputStreamReader(connection.getInputStream()).readAll();
            Logger.json(response);
            chatResponseDTO = new Gson().fromJson(response, ChatResponseDTO.class);
        } catch (IOException e) {
            throw new ClientOpenAiException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return chatResponseDTO;
    }
}
