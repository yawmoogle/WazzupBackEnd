package com.fdm.pod.pod.exception;

public class CommentDoesNotExistException extends RuntimeException {
    public CommentDoesNotExistException(){
        super("Comment you are looking for does not exist.");
    }
}
