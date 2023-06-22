package com.nisumlatam.registro.infrastructure.adapter.api.v1;

import com.nisumlatam.registro.application.port.LoginService;
import com.nisumlatam.registro.domain.request.LoginRequest;
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

public class LoginControllerTest {

    @Mock
    private LoginService loginService;
    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void login_Expect_ResponseOK() throws GenericException {
        LoginRequest loginRequest = buildRequest();
        when(loginService.login(loginRequest)).thenReturn(buildResponse());
        ResponseEntity<RegisterUserResponse> response = loginController.login(loginRequest);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
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

    private LoginRequest buildRequest() {
        return LoginRequest.builder()
                .email("jfrancomaro@gmail.com")
                .password("Nisum1234")
                .build();
    }
}
