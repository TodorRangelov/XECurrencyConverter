package com.domain.entities.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogDto {

    private Integer id;
    private String name;
    private String email;
    private String date;

    public UserLogDto() {
    }

    public UserLogDto(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
