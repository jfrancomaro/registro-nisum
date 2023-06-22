package com.nisumlatam.registro.domain.mapper;

import com.nisumlatam.registro.domain.entity.PhoneEntity;
import com.nisumlatam.registro.domain.entity.UserEntity;
import com.nisumlatam.registro.domain.request.RegisterUserRequest;
import com.nisumlatam.registro.domain.response.RegisterUserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterUserMapper {

    public static PhoneEntity toEntity(RegisterUserRequest.Phone request, String id){

        return PhoneEntity.builder()
                .user(UserEntity.builder()
                        .id(id)
                        .build())
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .number(request.getNumber())
                .build();
    }

    public static RegisterUserResponse.Phone toResponse(PhoneEntity entity){

        return RegisterUserResponse.Phone.builder()
                .cityCode(entity.getCityCode())
                .countryCode(entity.getCountryCode())
                .number(entity.getNumber())
                .build();
    }

    public static List<RegisterUserResponse.Phone> toResponse(List<PhoneEntity> entity){

        return entity.stream().map(RegisterUserMapper::toResponse).collect(Collectors.toList());
    }
}
