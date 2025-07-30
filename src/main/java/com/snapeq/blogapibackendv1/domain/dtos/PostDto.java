package com.snapeq.blogapibackendv1.domain.dtos;

import com.snapeq.blogapibackendv1.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private UUID id;
    private String title;
    private String content;
    private AuthorDto author;
    private PostStatus status; //may cause some trouble later
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
