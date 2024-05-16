package com.fdm.pod.pod.controller;

import java.util.List;
import com.fdm.pod.pod.model.Post;
import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.service.PostService;
import com.fdm.pod.pod.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Post> getPosts(){
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<Post> post(@RequestBody Post post, @RequestParam String username){
        logger.info("Received username: {}", username);
        ApplicationUser applicationUser = userService.getUserByUsername(username);
        if (applicationUser == null){
            logger.error("User not found: {}", username);
            return ResponseEntity.badRequest().body(null);
        }
        post.setApplicationUser(applicationUser);
        post.setCreateTime(java.time.LocalDateTime.now().toString());
        Post savedPost = postService.savePost(post);
        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/user/{user_id}")
    public List<Post> getPostsByUsername(@PathVariable String username){
        ApplicationUser applicationUser = userService.getUserByUsername(username);
        return postService.getPostsByApplicationUser(applicationUser);
    }

    @PostMapping("/{post-id}/edit")
    public Post post(@RequestBody Post post, @RequestParam Long postId){
        Post oldPost = postService.getPostById(postId);
        return postService.savePost(oldPost);
    }

    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }    
}
