package cz.lifecode.openaiclient.API.DTO.Exception;

public class ExceptionDTO {
    private ErrorDTO error;

    public ErrorDTO getError() {
        return error;
    }

    public void setError(ErrorDTO error) {
        this.error = error;
    }
}
