package cz.lifecode.openaiclient.API.Exceptions;

public class ServerOpenAiException extends OpenAiException {
    public ServerOpenAiException() {
    }

    public ServerOpenAiException(String message) {
        super(message);
    }

    public ServerOpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerOpenAiException(Throwable cause) {
        super(cause);
    }

    public ServerOpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
