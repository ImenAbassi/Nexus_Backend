package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

