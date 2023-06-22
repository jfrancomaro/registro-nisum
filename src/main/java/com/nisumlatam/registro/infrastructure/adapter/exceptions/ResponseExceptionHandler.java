package com.nisumlatam.registro.infrastructure.adapter.exceptions;

import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.GenericoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final GenericoResponse errorGenericResponse;
    private final GlobalMessages globalMessages;

    @Autowired
    public ResponseExceptionHandler(GenericoResponse errorGenericResponse, GlobalMessages globalMessages) {
        this.errorGenericResponse = errorGenericResponse;
        this.globalMessages = globalMessages;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<Object> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        GenericoResponse response = procesarError(
                globalMessages.msgValidationRequestFailed(
                        "",
                        fieldErrors.toString()
                )
        );
        log.warn(globalMessages.msgValidationRequestFailed(methodArgumentNotValidException.getBindingResult().getObjectName(), fieldErrors.toString()));
        log.warn(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(GenericException.class)
    public final ResponseEntity<GenericoResponse> manejarGenericException(GenericException genericException) {
        log.warn(genericException.getMessage(), genericException);
        GenericoResponse response = procesarError(genericException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<GenericoResponse> manejarException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorGenericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GenericoResponse procesarError(String mensaje) {
        GenericoResponse response = new GenericoResponse();
        response.setMensaje(mensaje);
        return response;
    }
}
