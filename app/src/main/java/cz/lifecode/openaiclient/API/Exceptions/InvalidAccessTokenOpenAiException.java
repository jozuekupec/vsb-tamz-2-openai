package cz.lifecode.openaiclient.API.Exceptions;

public class InvalidAccessTokenOpenAiException extends OpenAiException {
    public InvalidAccessTokenOpenAiException() {
    }

    public InvalidAccessTokenOpenAiException(String message) {
        super(message);
    }

    public InvalidAccessTokenOpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccessTokenOpenAiException(Throwable cause) {
        super(cause);
    }

    public InvalidAccessTokenOpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
