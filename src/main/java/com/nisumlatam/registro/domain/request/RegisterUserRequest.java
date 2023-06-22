package com.nisumlatam.registro.domain.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Data
public class RegisterUserRequest {

    @NotNull(message = "Ingrese un nombre")
    private String name;

    @Email(message = "no es un dirección de correo bien formada")
    @NotNull(message = "Ingrese un email")
    private String email;

    @Pattern(regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,}$", message = "Ingrese una contraseña con mayúscula, minúscula y mayor a 8 caracteres")
    @NotNull(message = "Ingrese una contraseña")
    private String password;

    @NotNull(message = "Ingrese un email")
    private List<Phone> phones;

    @Builder
    @Data
    public static class Phone {
        private String number;
        private String cityCode;
        private String countryCode;
    }
}
