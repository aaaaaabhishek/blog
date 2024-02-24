package com.blog.repositary;
import com.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostRepositary extends JpaRepository<Post,Long> {
}
