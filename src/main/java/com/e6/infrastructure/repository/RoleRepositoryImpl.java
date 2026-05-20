package com.e6.infrastructure.repository;

import com.e6.domain.model.Role;
import com.e6.domain.repository.RoleRepository;
import com.e6.infrastructure.entity.RoleEntity;
import com.e6.infrastructure.mapper.RoleMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RoleRepositoryImpl implements RoleRepository, PanacheRepositoryBase<RoleEntity, Integer> {

    private final RoleRepository roleRepository;

    @Inject
    public RoleRepositoryImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public Role findRoleById(Integer id) {
        RoleEntity roleEntity = findById(id);

        return RoleMapper.toDomain(roleEntity);
    }

    @Override
    public List<Role> getRoles() {
        return List.of();
    }

    @Override
    public void deleteRoleById(Integer id) {

    }
}
