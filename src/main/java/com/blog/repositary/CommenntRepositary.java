package com.blog.repositary;

import com.blog.entity.Comment;
import com.blog.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommenntRepositary extends JpaRepository<Comment,Long> {
  public List<Comment> findByPostId(long postId);
}
