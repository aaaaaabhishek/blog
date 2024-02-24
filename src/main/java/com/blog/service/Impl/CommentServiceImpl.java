package com.blog.service.Impl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repositary.CommenntRepositary;
import com.blog.repositary.PostRepositary;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepositary postRepo;
    private CommenntRepositary commenntRepo;
    public CommentServiceImpl(CommenntRepositary commenntRepo,PostRepositary postRepo) {
        this.commenntRepo = commenntRepo;
        this.postRepo=postRepo;
    }
     @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Page Not Found With id :"+postId)
        );
         Comment comment=new Comment();
         comment.setName(commentDto.getName());
         comment.setEmail(commentDto.getEmail());
         comment.setBody(commentDto.getBody());
         comment.setPost(post);
         Comment saveComment = commenntRepo.save(comment);
//         CommentDto dto=new CommentDto();
//         dto.setId(saveComment.getId());
//         dto.setName(saveComment.getName());
//         dto.setEmail(saveComment.getEmail());
//         dto.setBody(saveComment.getBody());
        return mapToDto(saveComment);
     }

    @Override
    public void deleteComment(long commentId) {
        Comment comment=commenntRepo.findById(commentId).orElseThrow(
            ()->new ResourceNotFoundException("comment not found"+commentId)
        );
        commenntRepo.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> comments = commenntRepo.findByPostId(postId);
        List<CommentDto> commentDtos = comments.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return commentDtos;
    }
    CommentDto mapToDto(Comment comment) {
        CommentDto dto=new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;
    }
}
