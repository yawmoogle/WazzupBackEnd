package com.fdm.pod.pod.exception;

public class PostDeletedOrRemovedException extends RuntimeException {
    public PostDeletedOrRemovedException(){
        super("Post has been deleted or removed.");
    }
}
