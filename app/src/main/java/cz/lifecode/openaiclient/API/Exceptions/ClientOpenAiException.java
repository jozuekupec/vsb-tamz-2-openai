package cz.lifecode.openaiclient.API.Exceptions;

public class ClientOpenAiException extends OpenAiException {
    public ClientOpenAiException() {
    }

    public ClientOpenAiException(String message) {
        super(message);
    }

    public ClientOpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientOpenAiException(Throwable cause) {
        super(cause);
    }

    public ClientOpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
