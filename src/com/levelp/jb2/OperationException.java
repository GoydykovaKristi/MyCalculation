package com.levelp.jb2;

public class OperationException extends Exception{
    public OperationException() {
    }

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationException(Throwable cause) {
        super(cause);
    }

    public OperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
