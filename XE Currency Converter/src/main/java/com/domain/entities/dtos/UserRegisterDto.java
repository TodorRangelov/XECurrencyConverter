package com.domain.entities.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {

    private String email;
    private String name;
    private String password;
    private String confirmPassword;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String name, String password, String confirmPassword) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
