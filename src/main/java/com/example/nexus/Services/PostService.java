package com.example.nexus.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.nexus.Entitie.Post;
import com.example.nexus.Repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // Créer un post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Mettre à jour un post
    public Post updatePost(Long id, Post updatedPost, MultipartFile newFile) {
        return postRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
    
            try {
                // Gérer les médias
                if (newFile != null && !newFile.isEmpty()) {
                    // Vérifier si le fichier est différent
                    if (isFileDifferent(newFile, post.getMediaUrl())) {
                        // Supprimer l'ancien fichier si remplacé
                        if (post.getMediaUrl() != null) {
                            deleteFile(post.getMediaUrl());
                        }
                        // Sauvegarder le nouveau fichier
                        String fileName = saveFile(newFile);
                        post.setMediaUrl(fileName);
    
                        // Définir le type de média
                        String contentType = newFile.getContentType();
                        if (contentType != null && contentType.startsWith("image")) {
                            post.setMediaType("IMAGE");
                        } else {
                            post.setMediaType("VIDEO");
                        }
                    }
                } else {
                    // Si aucun nouveau fichier n'est fourni, conserver les anciens médias
                    if (updatedPost.getMediaUrl() == null && post.getMediaUrl() != null) {
                        deleteFile(post.getMediaUrl());
                        post.setMediaUrl(null);
                        post.setMediaType(null);
                    }
                }
            } catch (IOException e) {
                // Journaliser l'erreur ou gérer l'exception
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la gestion du fichier : " + e.getMessage());
            }
    
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found"));
    }
    // Récupérer tous les posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Supprimer un post
    public void deletePost(Long id) {
        postRepository.findById(id).ifPresent(post -> {
            // Supprimer le fichier média associé, s'il existe
            if (post.getMediaUrl() != null) {
                deleteFile(post.getMediaUrl());
            }
            postRepository.delete(post);
        });
    }

    // Uploader un média
    public String uploadMedia(Long postId, MultipartFile file) throws IOException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Créer le répertoire de téléchargement s'il n'existe pas
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Générer un nom de fichier unique
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Sauvegarder le fichier
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Mettre à jour le post avec le média
        post.setMediaUrl(fileName);
        String contentType = file.getContentType();
        if (contentType != null && contentType.startsWith("image")) {
            post.setMediaType("IMAGE");
        } else {
            post.setMediaType("VIDEO");
        }
        postRepository.save(post);

        return fileName;
    }

    // Supprimer un fichier
    private void deleteFile(String mediaUrl) {
        Path path = Paths.get(UPLOAD_DIR + mediaUrl);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Vérifier si les fichiers sont différents
    private boolean isFileDifferent(MultipartFile newFile, String existingFileName) throws IOException {
        if (existingFileName == null || newFile == null) {
            return true;
        }
        Path existingFilePath = Paths.get(UPLOAD_DIR + existingFileName);
        byte[] existingFileContent = Files.readAllBytes(existingFilePath);
        byte[] newFileContent = newFile.getBytes();
        return !Arrays.equals(existingFileContent, newFileContent);
    }

    // Sauvegarder un fichier
    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}