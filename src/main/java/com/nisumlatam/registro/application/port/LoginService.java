package com.nisumlatam.registro.application.port;

import com.nisumlatam.registro.domain.request.LoginRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.GenericoResponse;

public interface LoginService {

    String getJWTToken(String usuario);

    GenericoResponse login(LoginRequest request) throws GenericException;
}
