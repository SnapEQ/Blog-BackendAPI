package com.snapeq.blogapibackendv1.services.impl;

import com.snapeq.blogapibackendv1.domain.PostStatus;
import com.snapeq.blogapibackendv1.domain.entities.CreatePostRequest;
import com.snapeq.blogapibackendv1.domain.entities.Post;
import com.snapeq.blogapibackendv1.domain.entities.UpdatePostRequest;
import com.snapeq.blogapibackendv1.domain.entities.User;
import com.snapeq.blogapibackendv1.repositories.PostRepository;
import com.snapeq.blogapibackendv1.services.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post getPost(UUID id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with ID " + id));
    }

    @Override // May add @Transactional later for tag implementation
    @Transactional
    public List<Post> getAllPosts() {
        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

    @Override
    @Transactional
    public Post createPost(User user, CreatePostRequest createPostRequest) {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setStatus(createPostRequest.getStatus());
        newPost.setAuthor(user);


        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest) {
        Post existingPost = postRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post does not exist with id = " + id));

        existingPost.setTitle(updatePostRequest.getTitle());
        String postContent = updatePostRequest.getContent(); //Review this code later on and delete this comment
        existingPost.setContent(postContent);
        existingPost.setStatus(updatePostRequest.getStatus());

        return postRepository.save(existingPost);
    }

    @Transactional
    @Override
    public void deletePost(UUID id) {
        Post post = getPost(id);
        postRepository.delete(post);
    }
}
