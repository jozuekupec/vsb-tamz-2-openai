package cz.lifecode.openaiclient.API.Exceptions;

public class IntegrationOpenAiException extends OpenAiException {
    public IntegrationOpenAiException() {
    }

    public IntegrationOpenAiException(String message) {
        super(message);
    }

    public IntegrationOpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegrationOpenAiException(Throwable cause) {
        super(cause);
    }

    public IntegrationOpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
