package cz.lifecode.openaiclient.API.Model;

import java.util.List;

import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;

public interface Model {
    List<String> getAvailableModelIds() throws OpenAiException;
}
