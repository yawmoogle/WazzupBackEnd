package com.fdm.pod.pod.service;

import java.util.List;
import java.util.Optional;

import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.model.Comment;
import com.fdm.pod.pod.model.Post;
import com.fdm.pod.pod.exception.CommentDoesNotExistException;
import com.fdm.pod.pod.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPost(Post post){
        return commentRepository.findByPost(post);
    }

    public List<Comment> getPostsByApplicationUser(ApplicationUser user){
        return commentRepository.findByApplicationUser(user);
    }

    public Comment getCommentById(Long commentId){
        try {
            return commentRepository.findById(commentId).get();
        } catch (Exception e) {
            throw new CommentDoesNotExistException();
        }
    }

    public boolean deleteComment(Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }
}
