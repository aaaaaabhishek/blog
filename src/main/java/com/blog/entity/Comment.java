package com.blog.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.DoubleStream;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comments")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private String email;
    private String name;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
