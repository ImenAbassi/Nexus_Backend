package com.example.nexus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Post;
import com.example.nexus.Entitie.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    // Find a reaction by post and user
    Reaction findByPostAndReactedBy(Post post, String reactedBy);

    // Find all reactions for a specific post
    List<Reaction> findByPost(Post post);
}
