package com.e6.application.usecase.user;

import com.e6.application.dto.RegisterUserDTO;
import com.e6.domain.model.Role;
import com.e6.domain.model.User;
import com.e6.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    @Inject
    public RegisterUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(RegisterUserDTO registerUserDTO){
        User user = new User();
        user.setEmail(registerUserDTO.getEmail());
        user.setFirstName(registerUserDTO.getFirstName());
        user.setPaternalSurname(registerUserDTO.getPaternalSurname());
        user.setMaternalSurname(registerUserDTO.getMaternalSurname());
        user.setActive(true);
        user.setRole(new Role(registerUserDTO.getRoleId()));

        return userRepository.create(user);
    }
}
