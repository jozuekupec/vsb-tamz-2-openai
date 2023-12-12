package cz.lifecode.openaiclient.API.Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonInputStreamReader {
    private final InputStream inputStream;

    public JsonInputStreamReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String readAll() {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException exception) {
            return "";
        }

        return stringBuilder.toString();
    }
}
