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

    public String register() {

        if (!password.equals(confirmPassword)) {

            return "index?faces-redirect=true";
        }

        if (userService.usernameTaken(this.username)) {
            return "index?faces-redirect=true";
        }

        userService.registerUser(this);

        return "logged?faces-redirect=true";
    }
}



