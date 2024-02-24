package com.blog.controller;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.service.PostService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postservice;
    public PostController(PostService postservice){
        this.postservice=postservice;
    }
//    @PostMapping
//    public ResponseEntity<String> createPost(@RequestBody PostDto postDto){
//        postservice.createPost(postDto);
//        return new ResponseEntity<>("Post iss created", HttpStatus.CREATED);
//    }
//@PreAuthorize("hasRole('ADMIN')")
@PostMapping
public ResponseEntity<?> createPost(@Valid  @RequestBody PostDto postDto, BindingResult bindingresult){
    PostDto Dto=postservice.createPost(postDto);
    if(bindingresult.hasErrors()){
        return new ResponseEntity<>(bindingresult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(Dto, HttpStatus.CREATED);
}
//    @PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
    postservice.deletePost(id);
    return new ResponseEntity<>("Post is Deleted",HttpStatus.OK);
}
//http:localhost:8080/api/posts?postid=1
//    @PreAuthorize("hasRole('ADMIN')")
@PutMapping
public ResponseEntity<PostDto> updatePost(@RequestParam("Postid")long Postid,@RequestBody PostDto postdto){
    PostDto dto=postservice.updatePost(Postid,postdto);
    return new ResponseEntity<>(dto,HttpStatus.OK);
}
//@GetMapping
//public List<PostDto> getALlPosts(){
//    List<PostDto> postDtos= postservice.getALlPosts();
//    return postDtos;
//}
    //http:localhost:8080/api/posts?pageNo=1&pageSize=5
@GetMapping
public ResponseEntity<List<PostDto>> getALlPosts(
        @RequestParam(name="pageNo",defaultValue="0",required=false)int pageNo,
        @RequestParam(name="pageSize",defaultValue="3",required=false)int pageSize,
        @RequestParam(name="sortBy",defaultValue="id",required=false)String sortBy,
        @RequestParam(name="sortDir",defaultValue="asc",required=false)String sortDir
        ){

    List<PostDto> postDtos= postservice.getALlPosts(pageNo,pageSize,sortBy,sortDir);
    return  new ResponseEntity<>(postDtos,HttpStatus.OK);
}
}
