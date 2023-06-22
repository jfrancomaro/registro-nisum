package com.nisumlatam.registro.application.port;

import com.nisumlatam.registro.domain.request.RegisterUserRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;

public interface RegisterUserService {

    RegisterUserResponse registerUser(RegisterUserRequest request) throws GenericException;
}
