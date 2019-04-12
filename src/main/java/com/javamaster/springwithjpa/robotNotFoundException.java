package com.javamaster.springwithjpa;

public class robotNotFoundException extends RuntimeException {

    public robotNotFoundException(String exception) {
        super(exception);
    }

}