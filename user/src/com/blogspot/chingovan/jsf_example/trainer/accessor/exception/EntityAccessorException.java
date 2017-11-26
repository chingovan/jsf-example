package com.blogspot.chingovan.jsf_example.trainer.accessor.exception;

/**
 * Created by ChiNV on 11/27/2017.
 */
public class EntityAccessorException extends Exception {
    public EntityAccessorException() {
    }

    public EntityAccessorException(String message) {
        super(message);
    }

    public EntityAccessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityAccessorException(Throwable cause) {
        super(cause);
    }

    public EntityAccessorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
