package com.fdm.pod.pod.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="post_id")
    private Long postId;

    @Column(name="create_time")
    private String createTime;

    @Column(name="update_time")
    private String updateTime;

    @Column(name="content")
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private ApplicationUser applicationUser;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    

    
}
