package cz.lifecode.openaiclient.API.Exceptions;

public class UnauthorizedOpenAiException extends OpenAiException {
    public UnauthorizedOpenAiException() {
    }

    public UnauthorizedOpenAiException(String message) {
        super(message);
    }

    public UnauthorizedOpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedOpenAiException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedOpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
