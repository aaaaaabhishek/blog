package com.blog.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tittle;
    private String description;
    private String content;
    @OneToMany(mappedBy="post",cascade = CascadeType.ALL,orphanRemoval = true)
    private  List<Comment>comments=new ArrayList<>();
}
