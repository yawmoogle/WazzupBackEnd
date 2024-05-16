package com.fdm.pod.pod.repository;

import java.util.List;

import com.fdm.pod.pod.model.ApplicationUser;
import com.fdm.pod.pod.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByApplicationUser(ApplicationUser applicationUser);
}
