package io.github.braayy.sqm.exception;

public final class SQMException extends Exception {

    public SQMException() {
    }

    public SQMException(String message) {
        super(message);
    }

    public SQMException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQMException(Throwable cause) {
        super(cause);
    }

    public SQMException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
