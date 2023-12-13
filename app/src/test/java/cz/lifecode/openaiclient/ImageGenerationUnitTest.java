package cz.lifecode.openaiclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.logging.Logger;

import cz.lifecode.openaiclient.API.Authorization;
import cz.lifecode.openaiclient.API.DTO.Image.ImageGenerationRequestDTO;
import cz.lifecode.openaiclient.API.DTO.Image.ImageGenerationResponseDTO;
import cz.lifecode.openaiclient.API.Exceptions.OpenAiException;
import cz.lifecode.openaiclient.API.Image.ImageGeneration;
import cz.lifecode.openaiclient.API.Image.Values.Model;
import cz.lifecode.openaiclient.API.Image.Values.Size;

public class ImageGenerationUnitTest {
    @Test
    public void response_isCorrect() {
        String apiKey = Configuration.getInstance().getOpenAiApiKey();
        ImageGeneration imageGeneration = new ImageGeneration(new Authorization(apiKey));

        ImageGenerationRequestDTO requestDTO = new ImageGenerationRequestDTO();
        requestDTO.setModel(Model.DALL_E_2);
        requestDTO.setN(2);
        requestDTO.setPrompt("Draw a sun that makes happy everyone looking at it.");
        requestDTO.setSize(Size.S512x512);

        ImageGenerationResponseDTO response;
        try {
            response = imageGeneration.requestImage(requestDTO);
        } catch (OpenAiException exception) {
            Logger.getGlobal().warning(exception.getMessage());
            Logger.getGlobal().info(exception.getExceptionDTOFromMessage().getError().getMessage());
            return;
        }

        assertEquals(2, response.getData().length);
        assertTrue(response.getData()[0].getUrl().length() > 0);

        requestDTO.setModel(Model.DALL_E_3);
        assertThrows(OpenAiException.class, () -> imageGeneration.requestImage(requestDTO));
    }
}
