package com.blog.service.Impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repositary.PostRepositary;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PostServiceImpl implements PostService {

    private PostRepositary postRepo;
    public PostServiceImpl(PostRepositary postRepo){
        this.postRepo=postRepo;
    }
    @Override
    public PostDto createPost(PostDto postdto){
        Post post=new Post();
        post.setTittle(postdto.getTittle());
        post.setContent(postdto.getContent());
        post.setDescription(postdto.getDescription());
        postRepo.save(post);
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTittle(post.getTittle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
      //  dto.setMessage("Post is created");
        return dto;
    }
    @Override
    public void deletePost(long id) {

       Post post=postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with id:"+id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public  PostDto updatePost(long postId, PostDto postdto) {
      Post post=postRepo.findById(postId).orElseThrow(
              ()->new ResourceNotFoundException("Page Not Found With id :"+postId)
      );
      post.setTittle(postdto.getTittle());
      post.setContent(postdto.getContent());
      post.setDescription(postdto.getDescription());
      Post savedPost=postRepo.save(post);
      PostDto dto=mapToDto(savedPost);
      return dto;
    }

//
//    @Override
//    public List<PostDto> getALlPosts() {
//        List<Post> posts=postRepo.findAll();
//        List<PostDto>dtos=posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());
//        return dtos;
//    }
//@Override
//public List<PostDto> getALlPosts(int pageNo, int pageSize) {
//    PageRequest pageable= PageRequest.of(pageNo,pageSize);
//    Page<Post> pageposts=postRepo.findAll(pageable);
//    List<Post> posts = pageposts.getContent();
//    List<PostDto>dtos=posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());
//    return dtos;
//}

    public List<PostDto> getALlPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
       // PageRequest pageable= PageRequest.of(pageNo,pageSize, Sort.by(sortDir));
        PageRequest pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pageposts=postRepo.findAll(pageable);
        List<Post> posts = pageposts.getContent();
        List<PostDto>dtos=posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }
  PostDto  mapToDto(Post post){
      PostDto dto=new PostDto();
      dto.setId(post.getId());
      dto.setTittle(post.getTittle());
      dto.setContent(post.getContent());
      dto.setDescription(post.getDescription());
      return dto;
    }

}
