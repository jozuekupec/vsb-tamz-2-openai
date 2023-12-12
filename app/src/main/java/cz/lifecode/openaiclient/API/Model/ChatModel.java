package cz.lifecode.openaiclient.API.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cz.lifecode.openaiclient.API.Authorization;

public class ChatModel extends BaseModel {
    public ChatModel(Authorization authorization) {
        super(authorization);
    }

    @Override
    protected List<String> filterModels(List<String> modelIds) {
        Stream<String> stream = modelIds.stream().filter(s -> s.contains("gpt-"));
        return stream.collect(Collectors.toList());
    }
}
