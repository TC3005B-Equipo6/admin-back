package com.e6.infrastructure.repository;

import com.e6.domain.exception.UserAlreadyExistsException;
import com.e6.domain.model.Role;
import com.e6.domain.model.User;
import com.e6.domain.repository.RoleRepository;
import com.e6.domain.repository.UserRepository;
import com.e6.infrastructure.entity.UserEntity;
import com.e6.infrastructure.firebase.FirebaseUserCreator;
import com.e6.infrastructure.mapper.UserMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, UUID> {

    private final FirebaseUserCreator firebaseUserCreator;
    private final RoleRepository roleRepository;

    @Inject
    public UserRepositoryImpl(FirebaseUserCreator firebaseUserCreator, RoleRepository roleRepository){
        this.firebaseUserCreator = firebaseUserCreator;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User create(User user) throws UserAlreadyExistsException{
        try {
            UserRecord firebaseRecord = firebaseUserCreator.registerUser(user.getEmail());
            user.setFirebaseUuid(firebaseRecord.getUid());

            Role role = roleRepository.findRoleById(user.getRole().getId());

            Map<String, Object> claims = new HashMap<>();
            claims.put("role", role.getName());
            FirebaseAuth.getInstance().setCustomUserClaims(firebaseRecord.getUid(), claims);

            user.setRole(role);
            user.setId(UUID.randomUUID());

            UserEntity userEntity = UserMapper.toEntity(user);
            persist(userEntity);
            return UserMapper.toDomain(userEntity);
        } catch (FirebaseAuthException e) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
    }

    @Override
    public User findUserById(UUID id){
        return UserMapper.toDomain(findById(id));
    }

    @Override
    public List<User> findUserByRole(int roleId){

        List<UserEntity> users = list("role.id", roleId);
        return users
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id){
        UserEntity userEntity = findById(id);
        if  (userEntity != null) {
            delete(userEntity);
        }
    }
}
