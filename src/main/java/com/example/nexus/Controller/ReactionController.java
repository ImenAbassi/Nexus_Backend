package com.example.nexus.Controller;

import com.example.nexus.Entitie.Reaction;
import com.example.nexus.Entitie.ReactionType;
import com.example.nexus.Services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reactions")
@CrossOrigin(origins = "http://localhost:4200")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping("/{postId}")
    public Reaction addReaction(@PathVariable Long postId,
                               @RequestParam ReactionType type,
                               @RequestParam String reactedBy) {
        return reactionService.addReaction(postId, type, reactedBy);
    }

    @GetMapping("/count/{postId}")
    public Map<ReactionType, Long> countReactions(@PathVariable Long postId) {
        return reactionService.countReactionsByPost(postId);
    }
}