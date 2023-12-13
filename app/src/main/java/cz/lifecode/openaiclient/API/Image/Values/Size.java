package cz.lifecode.openaiclient.API.Image.Values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import cz.lifecode.openaiclient.Engine.ScalarEnum.ScalarEnum;

public class Size implements ScalarEnum<String> {
    public static final String S256x256 = "256x256";
    public static final String S512x512 = "512x512";
    public static final String S1024x1024 = "1024x1024";
    public static final String S1792x1024 = "1792x1024";
    public static final String S1024x1792 = "1024x1792";

    @Override
    public List<String> getValues() {
        return Arrays.asList(
                S256x256,
                S512x512,
                S1024x1024,
                S1792x1024,
                S1024x1792
        );
    }

    public List<String> getValues(String model) {
        if (Objects.equals(model, Model.DALL_E_2)) {
            return Arrays.asList(
                    S256x256,
                    S512x512,
                    S1024x1024
            );
        } else if (Objects.equals(model, Model.DALL_E_3)) {
            return Arrays.asList(
                    S1024x1024,
                    S1792x1024,
                    S1024x1792
            );
        }

        return new ArrayList<>();
    }
}
