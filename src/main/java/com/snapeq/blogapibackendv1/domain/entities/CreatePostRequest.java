package com.snapeq.blogapibackendv1.domain.entities;

import com.snapeq.blogapibackendv1.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostRequest {

    private String title;

    private String content;

    private PostStatus status;

}
