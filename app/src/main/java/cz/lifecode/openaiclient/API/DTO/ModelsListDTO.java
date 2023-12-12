package cz.lifecode.openaiclient.API.DTO;

final public class ModelsListDTO {
    String object;
    ModelDTO[] data;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public ModelDTO[] getData() {
        return data;
    }

    public void setData(ModelDTO[] data) {
        this.data = data;
    }
}
