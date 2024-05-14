package com.fdm.pod.pod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{post_id}")
    
    
}
