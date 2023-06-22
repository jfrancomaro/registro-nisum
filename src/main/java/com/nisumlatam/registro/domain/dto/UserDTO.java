package com.nisumlatam.registro.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate lastLogin;
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
