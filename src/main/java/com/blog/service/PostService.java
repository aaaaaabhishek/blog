package com.blog.service;
import com.blog.payload.PostDto;
import java.util.List;
public interface PostService {
    public PostDto createPost(PostDto postdto);
    public void deletePost(long id);
    public  PostDto  updatePost(long id, PostDto dto);

  //  List<PostDto> getALlPosts();

   // List<PostDto> getALlPosts(int pageNo, int pageSize);
    public List<PostDto> getALlPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    }
