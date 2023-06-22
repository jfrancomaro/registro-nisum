package com.nisumlatam.registro.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @ApiModelProperty(required = true)
    @NotNull(message = "Ingrese un nombre")
    private String name;

    @ApiModelProperty(required = true)
    @Email(message = "no es un dirección de correo bien formada")
    @NotNull(message = "Ingrese un email")
    private String email;

    @ApiModelProperty(required = true)
    @Pattern(regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,}$", message = "Ingrese una contraseña con mayúscula, minúscula y mayor a 8 caracteres")
    @NotNull(message = "Ingrese una contraseña")
    private String password;

    @ApiModelProperty(required = true)
    @NotNull(message = "Ingrese telefonos")
    private List<Phone> phones;

    @Builder
    @Data
    public static class Phone {
        @ApiModelProperty(required = true)
        @NotNull(message = "Ingrese un numero")
        private String number;
        @ApiModelProperty(required = true)
        @NotNull(message = "Ingrese un codigo de ciudad")
        private String cityCode;
        @ApiModelProperty(required = true)
        @NotNull(message = "Ingrese un codigo de país")
        private String countryCode;
    }
}
