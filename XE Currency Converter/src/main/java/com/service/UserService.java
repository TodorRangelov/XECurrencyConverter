package com.service;

import com.domain.entities.User;
import com.domain.entities.dtos.UserLogDto;
import com.domain.entities.dtos.UserRegisterDto;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    UserLogDto loginUser(String email, String password);

//    String getLoginUserEmail();

    String logoutUser();

    User getLoginUser();

    UserLogDto getUserLogDto();
}
