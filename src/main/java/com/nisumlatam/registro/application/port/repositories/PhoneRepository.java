package com.nisumlatam.registro.application.port.repositories;

import com.nisumlatam.registro.domain.entity.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneEntity, Integer> {
}
