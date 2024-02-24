package com.blog.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Setter
@Getter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    private String name;
}

