package cz.lifecode.openaiclient.API.Image.Values;

import java.util.Arrays;
import java.util.List;

import cz.lifecode.openaiclient.Engine.ScalarEnum.ScalarEnum;

public class Model implements ScalarEnum<String> {
    public static final String DALL_E_2 = "dall-e-2";
    public static final String DALL_E_3 = "dall-e-3";

    @Override
    public List<String> getValues() {
        return Arrays.asList(
                DALL_E_2,
                DALL_E_3
        );
    }
}
