package cz.lifecode.openaiclient.API.DTO.Image;

public class ImageGenerationResponseDTO {
    private int created;
    private ImageGenerationDataResponseDTO[] data;

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public ImageGenerationDataResponseDTO[] getData() {
        return data;
    }

    public void setData(ImageGenerationDataResponseDTO[] data) {
        this.data = data;
    }
}
