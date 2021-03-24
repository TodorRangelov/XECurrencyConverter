package com.service;

import com.domain.entities.Role;
import com.domain.entities.User;
import com.domain.entities.dtos.UserRegisterDto;
import com.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    private String loginUserEmail;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        this.loginUserEmail = "";
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();

        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            return String.format("%s already exists", userRegisterDto.getEmail());
        }

        User byId = userRepository.findById(1).orElse(null);

        User user = modelMapper.map(userRegisterDto, User.class);

        if (byId == null) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

       sb.append(validateEmailAndPassword(user));
        if (!sb.isEmpty()) {
            return String.join("\n", sb);
        }

        this.userRepository.saveAndFlush(user);

        return String.format("%s was registered", user.getName());
    }

    @Override
    public String loginUser(String email, String password) {

        User byEmail = userRepository.findByEmail(email).orElse(null);

        if (byEmail == null) {
            return "Incorrect email";
        }

        if (!byEmail.getPassword().equals(password)) {
            return "Incorrect password";
        }

        this.loginUserEmail = email;

        return String.format("Successfully logged in %s", byEmail.getName());
    }

    private StringBuilder validateEmailAndPassword(User user) {
        StringBuilder sb = new StringBuilder();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        if (validate.size() > 0) {
            for (ConstraintViolation<User> userConstraintViolation : validate) {
                sb.append(userConstraintViolation.getMessage()).append(System.lineSeparator());
            }
        }

        return sb;
    }

    public String logoutUser() {
        String s = "User with email " + this.loginUserEmail + " successfully logged out";
        this.loginUserEmail = "";
        return s;
    }

    @Override
    public User getLoginUser() {
        return this.userRepository.getUserByEmail(this.loginUserEmail).orElse(null);
    }

    public String getLoginUserEmail() {
        return loginUserEmail;
    }
}
