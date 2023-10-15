package bg.bulsi.service;

import bg.bulsi.controller.RegisterBean;
import bg.bulsi.dao.UserDAO;
import bg.bulsi.model.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

@Named
@ApplicationScoped
public class UserService {

    @Inject
    private UserDAO userDAO;

    @PostConstruct
    public void init() {
        String salt = BCrypt.gensalt();
        UserEntity userEntity = new UserEntity(
                "admin",
                BCrypt.hashpw("12345", salt),
                "Pavel",
                "Pindarev",
                LocalDate.of(2004, 7, 22)
        );
        userDAO.saveUser(userEntity);
    }

    public boolean usernameTaken(String username) {
        return userDAO.findByUsername(username) != null;
    }

    public void registerUser(RegisterBean registerBean) {
        String salt = BCrypt.gensalt();
        UserEntity userEntity = new UserEntity(
                registerBean.getUsername(),
                BCrypt.hashpw(registerBean.getPassword(), salt),
                registerBean.getFirstName(),
                registerBean.getLastName(),
                registerBean.getBirthDate()
        );
        userDAO.saveUser(userEntity);
    }

    public UserEntity findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
