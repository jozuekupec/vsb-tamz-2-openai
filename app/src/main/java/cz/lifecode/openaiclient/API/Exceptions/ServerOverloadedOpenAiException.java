package cz.lifecode.openaiclient.API.Exceptions;

public class ServerOverloadedOpenAiException extends ServerOpenAiException {
    public ServerOverloadedOpenAiException() {
    }

    public ServerOverloadedOpenAiException(String message) {
        super(message);
    }

    public ServerOverloadedOpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerOverloadedOpenAiException(Throwable cause) {
        super(cause);
    }

    public ServerOverloadedOpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
