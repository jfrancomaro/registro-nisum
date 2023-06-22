package com.nisumlatam.registro.application.port;

import com.nisumlatam.registro.domain.request.LoginRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;

public interface LoginService {

    String getJWTToken(String usuario);

    RegisterUserResponse login(LoginRequest request) throws GenericException;
}
