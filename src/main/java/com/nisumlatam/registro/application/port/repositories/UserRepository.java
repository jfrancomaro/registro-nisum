package com.nisumlatam.registro.application.port.repositories;

import com.nisumlatam.registro.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    boolean existsByEmail(String email);

    UserEntity findByEmailAndPasswordAndIsActive(String email, String password, Boolean isActive);
}
