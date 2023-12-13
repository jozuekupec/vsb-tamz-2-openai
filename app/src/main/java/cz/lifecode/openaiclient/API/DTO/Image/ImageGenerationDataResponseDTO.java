package cz.lifecode.openaiclient.API.DTO.Image;

public class ImageGenerationDataResponseDTO {
    private String revised_prompt;
    private String url;

    public String getRevised_prompt() {
        return revised_prompt;
    }

    public void setRevised_prompt(String revised_prompt) {
        this.revised_prompt = revised_prompt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
