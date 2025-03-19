package com.example.nexus.Services;

import java.time.LocalDateTime;
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

    public Reaction addReaction(Long postId, ReactionType type, String reactedBy) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    
        // Vérifier si l'utilisateur a déjà réagi à ce post
        Reaction existingReaction = reactionRepository.findByPostAndReactedBy(post, reactedBy);
    
        if (existingReaction != null) {
            // Si l'utilisateur a déjà réagi, mettre à jour la réaction existante
            ReactionType oldType = existingReaction.getType();
            post.getReactionCounts().put(oldType, post.getReactionCounts().getOrDefault(oldType, 0L) - 1);
            existingReaction.setType(type);
            post.getReactionCounts().put(type, post.getReactionCounts().getOrDefault(type, 0L) + 1);
            return reactionRepository.save(existingReaction);
        } else {
            // Sinon, créer une nouvelle réaction
            Reaction reaction = new Reaction();
            reaction.setPost(post);
            reaction.setType(type);
            reaction.setReactedBy(reactedBy);
            post.getReactionCounts().put(type, post.getReactionCounts().getOrDefault(type, 0L) + 1);
            return reactionRepository.save(reaction);
        }
    }

    public Map<ReactionType, Long> countReactionsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    
        // Fetch all reactions for the post and group them by type
        return reactionRepository.findByPost(post).stream()
                .collect(Collectors.groupingBy(Reaction::getType, Collectors.counting()));
    }
}