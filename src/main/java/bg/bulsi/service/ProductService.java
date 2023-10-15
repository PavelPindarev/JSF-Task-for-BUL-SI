package bg.bulsi.service;

import bg.bulsi.dao.ProductDAO;
import bg.bulsi.model.entity.ProductEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ProductService {
    @Inject
    private ProductDAO productDao;


    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> allProducts = productDao.getAllProducts();
        return allProducts.isEmpty() ? new ArrayList<>() : allProducts;
    }

    public ProductEntity getProductById(Long id) {
        return productDao.getProductById(id);
    }

    public void addProduct(ProductEntity product) {
        productDao.addProduct(product);
    }

    public void updateProduct(Long productId, ProductEntity product) {
        ProductEntity entity = productDao.getProductById(productId);
        entity.setCategoryType(product.getCategoryType());
        entity.setDescription(product.getDescription());
        entity.setWeight(product.getWeight());
        entity.setPrice(product.getPrice());
        entity.setName(product.getName());
        productDao.updateProduct(entity);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}