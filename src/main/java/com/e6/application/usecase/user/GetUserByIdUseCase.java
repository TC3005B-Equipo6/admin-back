package com.e6.application.usecase.user;

import com.e6.domain.model.User;
import com.e6.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class GetUserByIdUseCase {

    private final UserRepository userRepository;

    @Inject
    public GetUserByIdUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(UUID id){
        return userRepository.findUserById(id);
    }
}
