package com.nisumlatam.registro.domain.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericException extends Exception {

    private int codigoError;

    public GenericException(String mensaje) {
        super(mensaje);
    }

    public GenericException(String mensaje, int codigoError, Object tipoReturn) {
        super(mensaje);
        this.codigoError = codigoError;
    }
}