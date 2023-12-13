package cz.lifecode.openaiclient.API.Exceptions;

public class LimitationOpenAiException extends OpenAiException {
    public LimitationOpenAiException() {
    }

    public LimitationOpenAiException(String message) {
        super(message);
    }

    public LimitationOpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitationOpenAiException(Throwable cause) {
        super(cause);
    }

    public LimitationOpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
