package com.nisumlatam.registro.domain.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class LoginRequest {

    @Email(message = "No es un dirección de correo bien formada")
    @NotNull(message = "Ingrese un email")
    private String email;

    @NotNull(message = "Ingrese su contraseña")
    private String password;
}
