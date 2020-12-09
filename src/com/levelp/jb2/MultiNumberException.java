package com.levelp.jb2;

public class MultiNumberException extends Exception{
    public MultiNumberException() {
    }

    public MultiNumberException(String message) {
        super(message);
    }

    public MultiNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultiNumberException(Throwable cause) {
        super(cause);
    }

    public MultiNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
