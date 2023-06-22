package com.nisumlatam.registro.infrastructure.adapter.api.v1;

import com.nisumlatam.registro.application.port.RegisterUserService;
import com.nisumlatam.registro.domain.request.RegisterUserRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class RegisterUserControllerTest {

    @Mock
    private RegisterUserService registerUserService;
    @InjectMocks
    private RegisterUserController registerUserController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerUser_Expect_ResponseOK() throws GenericException {
        RegisterUserRequest registerUserRequest = buildRequest();
        when(registerUserService.registerUser(registerUserRequest)).thenReturn(buildResponse());
        ResponseEntity<RegisterUserResponse> response = registerUserController.registerUser(registerUserRequest);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody());
    }

    private RegisterUserResponse buildResponse() {
        return RegisterUserResponse.builder()
                .id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .email("jfrancomaro@gmail.com")
                .password("Nisum1234")
                .name("Franco Martinez")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .phones(List.of(RegisterUserResponse.Phone.builder()
                        .number("980187759")
                        .cityCode("21")
                        .countryCode("51")
                        .build()))
                .build();
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
}
