package com.example.nexus.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Post;
import com.example.nexus.Entitie.Reaction;
import com.example.nexus.Entitie.ReactionType;
import com.example.nexus.Repository.PostRepository;
import com.example.nexus.Repository.ReactionRepository;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private PostRepository postRepository;

    public Reaction addReaction(Long postId, ReactionType reactionType, String reactedBy) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setType(reactionType);
        reaction.setReactedBy(reactedBy);
        reaction.setReactedAt(LocalDateTime.now());

        return reactionRepository.save(reaction);
    }

    public Map<ReactionType, Long> countReactionsByPost(Long postId) {
        List<Reaction> reactions = reactionRepository.findByPostId(postId);

        return reactions.stream()
                .collect(Collectors.groupingBy(Reaction::getType, Collectors.counting()));
    }
}
