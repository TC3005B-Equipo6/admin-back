package com.e6.domain.repository;

import com.e6.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    User create(User user);
    User findUserById(UUID id);
    List<User> findUserByRole(int roleId);
    void deleteUserById(UUID id);
}
