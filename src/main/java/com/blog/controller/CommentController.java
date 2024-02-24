package com.blog.controller;
import com.blog.payload.CommentDto;
import com.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }
   //http://localhost:8080/api/comments?postId=3
    @PostMapping
    public ResponseEntity<CommentDto>createComment(
            @RequestParam("postId") long postId, @RequestBody CommentDto commentDto
            ){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String>deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Coomment is deleted", HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable long postId){
        List<CommentDto> commentDtos=commentService.getCommentByPostId(postId);
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }
}
