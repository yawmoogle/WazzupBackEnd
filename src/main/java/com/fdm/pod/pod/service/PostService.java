package com.fdm.pod.pod.service;

import java.util.List;

import com.fdm.pod.pod.model.Post;
import com.fdm.pod.pod.exception.PostDoesNotExistException;
import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }
    
    public List<Post> getPostsByApplicationUser(ApplicationUser applicationUser){
        return postRepository.findByApplicationUser(applicationUser);
    }

    public Post getPostById(Long postId){
        try {
            return postRepository.findById(postId).get();
        } catch (Exception e) {
            throw new PostDoesNotExistException();
        }
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}
