package com.example.nexus.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.nexus.Entitie.Post;
import com.example.nexus.Services.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private PostService postService;

    // Créer un post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // Mettre à jour un post

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestPart("post") Post post, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Post updatedPost = postService.updatePost(id, post, file);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Récupérer tous les posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Supprimer un post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Uploader un média
    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadMedia(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = postService.uploadMedia(id, file);
            return ResponseEntity.ok("Fichier enregistré sous le nom : " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'upload : " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Post non trouvé : " + e.getMessage());
        }
    }

    // Récupérer un média en base64
    @GetMapping("/media/{fileName}/base64")
    public ResponseEntity<String> getMediaBase64(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            if (!Files.exists(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fichier non trouvé");
            }
            byte[] fileContent = Files.readAllBytes(filePath);
            String base64Image = Base64.getEncoder().encodeToString(fileContent);
            return ResponseEntity.ok(base64Image); // Retourne uniquement les données en base64
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de l'image : " + e.getMessage());
        }
    }
}