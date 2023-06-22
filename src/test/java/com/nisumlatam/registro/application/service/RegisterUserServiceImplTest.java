package com.nisumlatam.registro.application.service;

import com.nisumlatam.registro.application.port.LoginService;
import com.nisumlatam.registro.application.port.repositories.PhoneRepository;
import com.nisumlatam.registro.application.port.repositories.UserRepository;
import com.nisumlatam.registro.domain.entity.PhoneEntity;
import com.nisumlatam.registro.domain.entity.UserEntity;
import com.nisumlatam.registro.domain.request.RegisterUserRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;
import com.nisumlatam.registro.infrastructure.adapter.exceptions.GlobalMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RegisterUserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PhoneRepository phoneRepository;
    @Mock
    private LoginService loginService;
    @Mock
    private GlobalMessages globalMessages;
    @InjectMocks
    private RegisterUserServiceImpl registerUserService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerUser_Expect_ResponseOK() throws GenericException {
        UserEntity userEntity = buildUserEntity();
        PhoneEntity phoneEntity = buildPhoneEntity();
        RegisterUserRequest registerUserRequest = buildRequest();
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJuaXN1bWxhdGFtIiwic3ViIjoiRnJhbmNvIE1hcnRpbmV6IiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY4NzQ1ODY2MSwiZXhwIjoxNjg3NDU5MjYxfQ.JAsx4Wm0XbuZOaFYueEaM2paoM7On3TSokL8qcJtK-CL7HoonZaqWJMO8N7sabkEeo4_jLPlvQOYGSgKQrC76A";
        when(userRepository.existsByEmail(registerUserRequest.getEmail())).thenReturn(false);
        when(loginService.getJWTToken(registerUserRequest.getName())).thenReturn(token);
        when(userRepository.save(any())).thenReturn(userEntity);
        when(phoneRepository.save(phoneEntity)).thenReturn(phoneEntity);
        RegisterUserResponse response = registerUserService.registerUser(registerUserRequest);
        assertNotNull(response);
    }

    @Test
    public void registerUser_Expect_ResponseGenericException() {
        RegisterUserRequest registerUserRequest = buildRequest();
        String message = "El correo ya existe";
        when(userRepository.existsByEmail(registerUserRequest.getEmail())).thenReturn(true);
        when(globalMessages.msgValidationEmailFailed()).thenReturn(message);
        assertThrows(GenericException.class, () -> registerUserService.registerUser(registerUserRequest));
    }

    private RegisterUserRequest buildRequest() {
        return RegisterUserRequest.builder()
                .email("jfrancomaro@gmail.com")
                .password("Nisum1234")
                .name("Franco Martinez")
                .phones(List.of(RegisterUserRequest.Phone.builder()
                        .number("980187759")
                        .cityCode("21")
                        .countryCode("51")
                        .build()))
                .build();
    }

    private UserEntity buildUserEntity() {
        return UserEntity.builder()
                .id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .email("jfrancomaro@gmail.com")
                .password("Nisum1234")
                .name("Franco Martinez")
                .phones(List.of(PhoneEntity.builder()
                        .user(UserEntity.builder()
                                .id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                                .build())
                        .number("980187759")
                        .cityCode("21")
                        .countryCode("51")
                        .build()))
                .build();
    }

    private PhoneEntity buildPhoneEntity() {
        return PhoneEntity.builder()
                .user(UserEntity.builder()
                        .id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                        .build())
                .number("980187759")
                .cityCode("21")
                .countryCode("51")
                .build();
    }
}
