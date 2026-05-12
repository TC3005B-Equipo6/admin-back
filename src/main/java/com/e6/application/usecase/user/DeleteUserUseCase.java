package com.e6.application.usecase.user;

import com.e6.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    @Inject
    public DeleteUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void execute(UUID id){
        userRepository.deleteUserById(id);
    }
}
