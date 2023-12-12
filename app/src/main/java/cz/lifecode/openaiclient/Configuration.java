package cz.lifecode.openaiclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Configuration instance = null;
    private static final String configurationFilePath = "src/main/res/raw/local_configuration_properties";
    private final Properties properties = new Properties();

    private Configuration() {
        try (FileInputStream fileInputStream = new FileInputStream(configurationFilePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }

        return instance;
    }

    public String getOpenAiApiKey() {
        return properties.getProperty("api.openai.apiKey");
    }

}
