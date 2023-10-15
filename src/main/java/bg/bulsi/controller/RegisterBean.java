package bg.bulsi.controller;


import bg.bulsi.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.*;

import java.time.LocalDate;

@Named(value = "registerBean")
@RequestScoped
@Getter
@Setter
public class RegisterBean {

    private String username;

    private String password;

    private String confirmPassword;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Inject
    private UserService userService;


}

