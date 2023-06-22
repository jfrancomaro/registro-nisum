package com.nisumlatam.registro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TBL_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(length = 36, nullable = false, name = "ID")
    private String id;

    @Column(length = 100, nullable = false, name = "NAME")
    private String name;

    @Column(length = 100, nullable = false, name = "EMAIL")
    private String email;

    @Column(length = 100, nullable = false, name = "PASSWORD")
    private String password;

    @Column(columnDefinition = "TIMESTAMP", name = "CREATED")
    private LocalDateTime created;

    @Column(columnDefinition = "TIMESTAMP", name = "MODIFIED")
    private LocalDateTime modified;

    @Column(columnDefinition = "TIMESTAMP", name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column(length = 1000, name = "TOKEN")
    private String token;

    @Column(columnDefinition = "BIT", name = "IS_ACTIVE")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
    private List<PhoneEntity> phones;
}
