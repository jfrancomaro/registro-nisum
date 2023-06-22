package com.nisumlatam.registro.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PHONE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 12, nullable = false, name = "NUMBER")
    private String number;

    @Column(length = 3, nullable = false, name = "CITY_CODE")
    private String cityCode;

    @Column(length = 3, nullable = false, name = "COUNTRY_CODE")
    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    @JsonIgnore
    private UserEntity user;
}
