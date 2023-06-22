package com.nisumlatam.registro.application.service;

import com.nisumlatam.registro.application.port.repositories.UserRepository;
import com.nisumlatam.registro.domain.entity.PhoneEntity;
import com.nisumlatam.registro.domain.entity.UserEntity;
import com.nisumlatam.registro.domain.request.LoginRequest;
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
import static org.mockito.Mockito.when;

public class LoginServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private GlobalMessages globalMessages;
    @InjectMocks
    private LoginServiceImpl loginService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void login_Expect_ResponseOK() throws GenericException {
        UserEntity userEntity = buildEntity();
        LoginRequest loginRequest = buildRequest();
        when(userRepository.findByEmailAndPasswordAndIsActive(loginRequest.getEmail(),
                loginRequest.getPassword(), true)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        RegisterUserResponse response = loginService.login(loginRequest);
        assertNotNull(response);
    }

    @Test
    public void login_Expect_ResponseGenericException() {
        String message = "El usuario no existe";
        LoginRequest loginRequest = buildRequest();
        when(userRepository.findByEmailAndPasswordAndIsActive(loginRequest.getEmail(),
                loginRequest.getPassword(), true)).thenReturn(null);
        when(globalMessages.msgValidationUserExists()).thenReturn(message);
        assertThrows(GenericException.class, () -> loginService.login(loginRequest));
    }

    private LoginRequest buildRequest() {
        return LoginRequest.builder()
                .email("jfrancomaro@gmail.com")
                .password("Nisum1234")
                .build();
    }

    private UserEntity buildEntity() {
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
}
