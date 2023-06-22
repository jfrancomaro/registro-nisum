package com.nisumlatam.registro.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class RegisterUserResponse {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;

    @Builder
    @Data
    public static class Phone {
        private String number;
        private String cityCode;
        private String countryCode;
    }
}
