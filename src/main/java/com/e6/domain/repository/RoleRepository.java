package com.e6.domain.repository;

import com.e6.domain.model.Role;

import java.util.List;

public interface RoleRepository {
    Role create(Role role);
    Role findRoleById(Integer id);
    List<Role> getRoles();
    void deleteRoleById(Integer id);
}
