package cz.lifecode.openaiclient.API.Image;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.DTO.Image.ImageGenerationRequestDTO;
import cz.lifecode.openaiclient.API.DTO.Image.ImageGenerationResponseDTO;
import cz.lifecode.openaiclient.API.Exceptions.ClientOpenAiException;
import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;
import cz.lifecode.openaiclient.API.OpenAiClient;
import cz.lifecode.openaiclient.API.Stream.JsonInputStreamReader;

public class ImageGeneration {
    protected static final String GENERATE_IMAGE_ENDPOINT = "https://api.openai.com/v1/images/generations";
    protected static final String GENERATE_IMAGE_METHOD = "POST";
    private final Authorization authorization;

    public ImageGeneration(Authorization authorization) {
        this.authorization = authorization;
    }

    public ImageGenerationResponseDTO requestImage(ImageGenerationRequestDTO requestDTO) throws OpenAiException {
        HttpURLConnection connection = null;
        ImageGenerationResponseDTO imageGenerationResponseDTO;

        try {
            URL url = new URL(GENERATE_IMAGE_ENDPOINT);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GENERATE_IMAGE_METHOD);
            connection.setRequestProperty("Authorization", authorization.getAuthorizationHeaderValue());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(45000); // Timeout nastaven na 45s
            connection.setReadTimeout(45000);
            connection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            outputStreamWriter.write(new Gson().toJson(requestDTO));
            outputStreamWriter.flush();

            connection.connect();
            OpenAiClient.handleConnection(connection);

            String response = new JsonInputStreamReader(connection.getInputStream()).readAll();
            imageGenerationResponseDTO = new Gson().fromJson(response, ImageGenerationResponseDTO.class);
        } catch (IOException e) {
            throw new ClientOpenAiException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return imageGenerationResponseDTO;
    }

}
