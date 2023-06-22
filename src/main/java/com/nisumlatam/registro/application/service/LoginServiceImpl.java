package com.nisumlatam.registro.application.service;

import com.nisumlatam.registro.application.port.LoginService;
import com.nisumlatam.registro.application.port.repositories.UserRepository;
import com.nisumlatam.registro.application.service.reusable.ReusableResponse;
import com.nisumlatam.registro.domain.entity.UserEntity;
import com.nisumlatam.registro.domain.request.LoginRequest;
import com.nisumlatam.registro.domain.response.GenericException;
import com.nisumlatam.registro.domain.response.GenericoResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl extends ReusableResponse implements LoginService {

    private final UserRepository userRepository;

    @Override
    public GenericoResponse login(LoginRequest request) throws GenericException {

        UserEntity userEntity = userRepository.findByEmailAndPasswordAndIsActive(request.getEmail(), request.getPassword(), true);
        if (Objects.isNull(userEntity)) throw new GenericException(globalMessages.msgValidacionUserExists());

        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setModified(LocalDateTime.now());
        userEntity.setToken(getJWTToken(userEntity.getName()));
        userEntity = userRepository.save(userEntity);
        return procesarRespuesta(userEntity, globalMessages.msgProcesoExitoso());
    }

    @Override
    public String getJWTToken(String usuario) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("nisumlatam")
                .setSubject(usuario)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
