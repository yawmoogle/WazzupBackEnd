package com.fdm.pod.pod.exception;

public class EmailTakenException extends RuntimeException {

    public EmailTakenException(){
        super("The email provided is already taken.");
    }
}
