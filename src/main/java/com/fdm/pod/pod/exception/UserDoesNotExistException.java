package com.fdm.pod.pod.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(){
        super("User you are looking for does not exist");
    }
}
