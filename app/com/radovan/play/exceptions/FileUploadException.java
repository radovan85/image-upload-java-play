package com.radovan.play.exceptions;

public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileUploadException(String message) {
        super(message);
    }
}
