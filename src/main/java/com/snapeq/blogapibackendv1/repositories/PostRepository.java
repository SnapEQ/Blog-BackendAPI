package com.snapeq.blogapibackendv1.repositories;

import com.snapeq.blogapibackendv1.domain.PostStatus;
import com.snapeq.blogapibackendv1.domain.entities.Post;
import com.snapeq.blogapibackendv1.domain.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @EntityGraph(attributePaths = "author")
    List<Post> findAllByStatus(PostStatus status);

    @EntityGraph(attributePaths = "author")
    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}
