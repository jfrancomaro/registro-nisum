package com.nisumlatam.registro.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class LoginRequest {

    @ApiModelProperty(required = true)
    @Email(message = "No es un dirección de correo bien formada")
    @NotNull(message = "Ingrese un email")
    private String email;

    @ApiModelProperty(required = true)
    @NotNull(message = "Ingrese su contraseña")
    private String password;
}
