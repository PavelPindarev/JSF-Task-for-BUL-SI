package bg.bulsi.dao;

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
public class ProductDAO {
    @Inject
    private SessionFactory sessionFactory;

    public List<ProductEntity> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            List<ProductEntity> products = session.createQuery("FROM ProductEntity", ProductEntity.class).getResultList();
            tx.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProductEntity getProductById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            tx.commit();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addProduct(ProductEntity product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(ProductEntity product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            ProductEntity product = session.get(ProductEntity.class, id);
            if (product != null) {
                session.remove(product);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

