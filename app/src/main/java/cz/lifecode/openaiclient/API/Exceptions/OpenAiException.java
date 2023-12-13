package cz.lifecode.openaiclient.API.Exceptions;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cz.lifecode.openaiclient.API.DTO.Exception.ExceptionDTO;

public class OpenAiException extends Exception {
    public OpenAiException() {
    }

    public OpenAiException(String message) {
        super(message);
    }

    public OpenAiException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenAiException(Throwable cause) {
        super(cause);
    }

    public OpenAiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @return Returns parsed JSON to exception object. In case of invalid JSON representation returns null.
     */
    public ExceptionDTO getExceptionDTOFromMessage() {
        try {
            return new Gson().fromJson(getMessage(), ExceptionDTO.class);
        } catch (JsonSyntaxException exception) {
            return null;
        }
    }
}
