package com.e6.application.usecase.user;

import com.e6.application.dto.UpdateUserDTO;
import com.e6.domain.model.User;
import com.e6.infrastructure.repository.UserRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class UpdateUserUseCase {

    private final UserRepositoryImpl userRepository;

    @Inject
    public UpdateUserUseCase(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UUID id, UpdateUserDTO dto) {
        return userRepository.updateUser(id, dto);
    }
}