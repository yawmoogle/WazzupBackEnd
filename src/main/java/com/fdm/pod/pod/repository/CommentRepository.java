package com.fdm.pod.pod.repository;

import java.util.List;

import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.model.Post;
import com.fdm.pod.pod.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByApplicationUser(ApplicationUser user);

    List<Comment> findByPost(Post post);

}
