package com.techyShubham.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techyShubham.entities.Comment;

public interface CommentRepo  extends JpaRepository<Comment	, Integer> {

}
