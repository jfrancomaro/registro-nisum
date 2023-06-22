package com.nisumlatam.registro.infrastructure.adapter.api.v1;

import com.nisumlatam.registro.application.port.RegisterUserService;
import com.nisumlatam.registro.domain.request.RegisterUserRequest;
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
@Api(tags = "Registro", description = "Servicio para registrar un usuario.")
@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserService registerUserService;

    @PostMapping
    @ApiOperation(
            value = "Registro",
            notes = "Este método es para registrar un usuario.")
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) throws GenericException {
        log.info("Start registerUser: {}", request);
        RegisterUserResponse response = registerUserService.registerUser(request);
        log.info("End registerUser {}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
