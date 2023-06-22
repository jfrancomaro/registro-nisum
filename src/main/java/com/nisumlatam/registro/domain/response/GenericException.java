package com.nisumlatam.registro.domain.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericException extends Exception {

    public GenericException(String mensaje) {
        super(mensaje);
    }
}