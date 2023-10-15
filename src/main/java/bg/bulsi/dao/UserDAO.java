package bg.bulsi.dao;

import bg.bulsi.model.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Named
@ApplicationScoped
public class UserDAO {

    @Inject
    private SessionFactory sessionFactory;

    public UserEntity findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            UserEntity user = session.createQuery("FROM UserEntity WHERE username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
            tx.commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveUser(UserEntity user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
