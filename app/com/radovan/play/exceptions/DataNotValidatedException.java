package com.radovan.play.exceptions;

public class DataNotValidatedException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DataNotValidatedException(String message) {
        super(message);
    }
}
