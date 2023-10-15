package bg.bulsi.controller;

import bg.bulsi.model.entity.UserEntity;
import bg.bulsi.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

@Named(value = "loginBean")
@RequestScoped
@Getter
@Setter
public class LoginBean {

    private String username;

    private String password;


    @Inject
    private UserService userService;

    public String login() {
        if (!userService.usernameTaken(this.username)) {
            return "index?faces-redirect=true";
        }
        UserEntity userEntity = userService.findUserByUsername(this.username);
        boolean passwordMatch = BCrypt.checkpw(this.password, userEntity.getPassword());
        if (!passwordMatch) {

            return "index?faces-redirect=true";
        }
        return "logged?faces-redirect=true";
    }

}