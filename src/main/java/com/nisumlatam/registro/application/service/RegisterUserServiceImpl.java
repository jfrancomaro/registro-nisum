package com.nisumlatam.registro.application.service;

import com.nisumlatam.registro.application.port.LoginService;
import com.nisumlatam.registro.application.port.RegisterUserService;
import com.nisumlatam.registro.application.port.repositories.PhoneRepository;
import com.nisumlatam.registro.application.port.repositories.UserRepository;
import com.nisumlatam.registro.domain.entity.PhoneEntity;
import com.nisumlatam.registro.domain.entity.UserEntity;
import com.nisumlatam.registro.domain.mapper.RegisterUserMapper;
import com.nisumlatam.registro.domain.request.RegisterUserRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;
import com.nisumlatam.registro.infrastructure.adapter.exceptions.GlobalMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegisterUserServiceImpl implements RegisterUserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final LoginService loginService;
    private final GlobalMessages globalMessages;

    @Override
    @Transactional
    public RegisterUserResponse registerUser(RegisterUserRequest request) throws GenericException {

        boolean existsByEmail = userRepository.existsByEmail(request.getEmail());
        if (existsByEmail) throw new GenericException(globalMessages.msgValidationEmailFailed());

        String token = loginService.getJWTToken(request.getName());

        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .token(token)
                .build());

        List<PhoneEntity> phones = request.getPhones().stream()
                .map(phone -> phoneRepository.save(RegisterUserMapper.toEntity(phone, userEntity.getId())))
                .collect(Collectors.toList());

        return RegisterUserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phones(RegisterUserMapper.toResponse(phones))
                .token(userEntity.getToken())
                .created(userEntity.getCreated())
                .modified(userEntity.getModified())
                .lastLogin(userEntity.getLastLogin())
                .isActive(userEntity.getIsActive())
                .build();
    }
}
