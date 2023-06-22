package com.nisumlatam.registro.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Phone {
        private String number;
        private String cityCode;
        private String countryCode;
    }
}
