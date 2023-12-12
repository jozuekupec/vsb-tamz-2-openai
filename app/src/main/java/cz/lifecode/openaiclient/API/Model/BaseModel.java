package cz.lifecode.openaiclient.API.Model;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.DTO.Model.ModelDTO;
import cz.lifecode.openaiclient.API.DTO.Model.ModelsListDTO;
import cz.lifecode.openaiclient.API.Stream.JsonInputStreamReader;

abstract public class BaseModel implements Model {
    protected static final String LIST_MODELS_ENDPOINT = "https://api.openai.com/v1/models";
    protected static final String LIST_MODELS_METHOD = "GET";
    private final Authorization authorization;
    private List<String> modelIds = null;

    BaseModel(Authorization authorization) {
        this.authorization = authorization;
    }

    public List<String> getAvailableModelIds() {
        if (modelIds == null) {
            fetch(); // TODO: Handle returned value
        }

        return modelIds;
    }

    protected boolean fetch() {
        HttpURLConnection connection = null;
        List<String> fetchedModelIds = new ArrayList<>();

        try {
            URL url = new URL(LIST_MODELS_ENDPOINT);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(LIST_MODELS_METHOD);
            connection.setRequestProperty("Authorization", authorization.getAuthorizationHeaderValue());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(15000); // Timeout nastaven na 15s
            connection.setReadTimeout(15000);
            connection.connect();

            if (connection.getResponseCode() > 299) {
                return false;
            }

            String response = new JsonInputStreamReader(connection.getInputStream()).readAll();
            ModelsListDTO models = new Gson().fromJson(response, ModelsListDTO.class);
            for (ModelDTO model: models.getData()) {
                fetchedModelIds.add(model.getId());
            }

        } catch (MalformedURLException exception) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        modelIds = filterModels(fetchedModelIds);
        return true;
    }

    abstract protected List<String> filterModels(List<String> modelIds);
}
