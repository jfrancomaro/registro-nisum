package com.nisumlatam.registro.infrastructure.adapter.api.v1;

import com.nisumlatam.registro.application.port.LoginService;
import com.nisumlatam.registro.domain.request.LoginRequest;
import com.nisumlatam.registro.domain.request.RegisterUserRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.GenericoResponse;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<GenericoResponse> login(@Valid @RequestBody LoginRequest request) throws GenericException {

        log.info("Inicio de login: usuario {}", request.getEmail());
        GenericoResponse response = loginService.login(request);
        log.info("Fin de login {}", response.getMensaje());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
