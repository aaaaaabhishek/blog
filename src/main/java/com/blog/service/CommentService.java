package com.blog.service;

import com.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto);

  public  void deleteComment(long commentId);

  public List<CommentDto> getCommentByPostId(long postId);
}
