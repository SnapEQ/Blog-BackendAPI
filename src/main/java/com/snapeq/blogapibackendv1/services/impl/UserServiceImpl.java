package com.snapeq.blogapibackendv1.services.impl;


import com.snapeq.blogapibackendv1.domain.entities.User;
import com.snapeq.blogapibackendv1.repositories.UserRepository;
import com.snapeq.blogapibackendv1.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

}
