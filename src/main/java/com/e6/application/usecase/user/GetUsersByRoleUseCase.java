package com.e6.application.usecase.user;

import com.e6.domain.model.User;
import com.e6.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetUsersByRoleUseCase
{

    private final UserRepository userRepository;

    @Inject
    public GetUsersByRoleUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> execute(int roleId){
        return userRepository.findUserByRole(roleId);
    }
}
