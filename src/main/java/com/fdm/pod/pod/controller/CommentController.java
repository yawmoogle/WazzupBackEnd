package com.fdm.pod.pod.controller;

import java.util.List;

import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.model.Post;
import com.fdm.pod.pod.model.Comment;
import com.fdm.pod.pod.service.PostService;
import com.fdm.pod.pod.service.CommentService;
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
@RequestMapping("/api/comments")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Comment> getComments(){
        return commentService.getAllComments();
    }

    @PostMapping
    public ResponseEntity<Comment> comment(@RequestBody Comment comment, @RequestParam Long postId, @RequestParam String username){
        logger.info("Received username: {}", username);
        ApplicationUser user = userService.getUserByUsername(username);
        if (user == null){
            logger.error("User not found: {}", username);
            return ResponseEntity.badRequest().body(null);
        }
        logger.info("Received post id: {}", postId);
        Post post = postService.getPostById(postId);
        if (post == null){
            logger.error("Post not found: {}", postId);
            return ResponseEntity.badRequest().body(null);
        }
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreateTime(java.time.LocalDateTime.now().toString());
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/post/{post_id}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId){
        Post post = postService.getPostById(postId);
        return commentService.getCommentsByPost(post);
    }

    @PostMapping("/comment/edit")
    public Comment comment(@RequestBody Comment comment, @RequestParam Long commentId){
        Comment oldComment = commentService.getCommentById(commentId);
        oldComment.setUpdateTime(java.time.LocalDateTime.now().toString());
        return commentService.saveComment(oldComment);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteComment(@RequestParam Long commentId){
        boolean deleted = commentService.deleteComment(commentId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

