package com.radovan.play.exceptions;

public class InstanceUndefinedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InstanceUndefinedException(String message) {
        super(message);
    }
}
