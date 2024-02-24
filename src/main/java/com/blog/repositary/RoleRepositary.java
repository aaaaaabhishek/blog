package com.blog.repositary;

import com.blog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositary extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
