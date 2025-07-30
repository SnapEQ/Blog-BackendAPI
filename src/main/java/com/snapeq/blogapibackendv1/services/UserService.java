package com.snapeq.blogapibackendv1.services;

import com.snapeq.blogapibackendv1.domain.entities.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);
}
