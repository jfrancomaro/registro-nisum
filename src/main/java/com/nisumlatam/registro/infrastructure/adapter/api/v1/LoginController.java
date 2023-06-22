package com.nisumlatam.registro.infrastructure.adapter.api.v1;

import com.nisumlatam.registro.application.port.LoginService;
import com.nisumlatam.registro.domain.request.LoginRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(tags = "Login", description = "Servicio para iniciar sesión.")
@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(
            value = "Iniciar sesión",
            notes = "Este método es para iniciar sesión y generar un token.")
    public ResponseEntity<RegisterUserResponse> login(@Valid @RequestBody LoginRequest request) throws GenericException {

        log.info("Start login: usuario {}", request.getEmail());
        RegisterUserResponse response = loginService.login(request);
        log.info("End login");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
