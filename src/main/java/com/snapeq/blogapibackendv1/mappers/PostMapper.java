package com.snapeq.blogapibackendv1.mappers;

import com.snapeq.blogapibackendv1.domain.dtos.CreatePostRequestDto;
import com.snapeq.blogapibackendv1.domain.dtos.PostDto;
import com.snapeq.blogapibackendv1.domain.dtos.UpdatePostRequestDto;
import com.snapeq.blogapibackendv1.domain.entities.CreatePostRequest;
import com.snapeq.blogapibackendv1.domain.entities.Post;
import com.snapeq.blogapibackendv1.domain.entities.UpdatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "status", source = "status")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);

}
