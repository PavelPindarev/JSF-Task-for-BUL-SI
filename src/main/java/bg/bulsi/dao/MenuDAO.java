package bg.bulsi.dao;

import bg.bulsi.model.entity.MenuEntity;
import bg.bulsi.model.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@Named
@ApplicationScoped
public class MenuDAO {
    @Inject
    private SessionFactory sessionFactory;

    public List<MenuEntity> getAllMenus() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            List<MenuEntity> menus = session.createQuery("FROM MenuEntity", MenuEntity.class).getResultList();
            tx.commit();
            return menus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MenuEntity getMenuById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            MenuEntity menu = session.get(MenuEntity.class, id);
            tx.commit();
            return menu;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addMenu(MenuEntity menu) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(menu);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMenu(MenuEntity menu) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(menu);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            MenuEntity menu = session.get(MenuEntity.class, id);
            if (menu != null) {
                session.delete(menu);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ProductEntity> getProductsForMenu(Long menuId) {
        // Retrieve the list of products associated with a specific menu based on `menuId`.
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            MenuEntity menu = session.get(MenuEntity.class, menuId);
            List<ProductEntity> products = menu.getProducts();
            tx.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
