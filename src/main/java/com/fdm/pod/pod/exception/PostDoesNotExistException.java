package com.fdm.pod.pod.exception;

public class PostDoesNotExistException extends RuntimeException {
    public PostDoesNotExistException(){
        super("Post you are looking for does not exist.");
    }
}
