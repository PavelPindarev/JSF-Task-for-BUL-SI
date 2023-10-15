package bg.bulsi.service;

import bg.bulsi.dao.MenuDAO;
import bg.bulsi.model.entity.MenuEntity;
import bg.bulsi.model.entity.ProductEntity;
import bg.bulsi.model.enums.CategoryType;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Named
@ApplicationScoped
public class MenuService {

    @Inject
    private MenuDAO menuDAO;

    @Inject
    private ProductService productService;

    @PostConstruct
    private void initDB() {
        MenuEntity menuEntity = new MenuEntity();
        this.addMenu(menuEntity);
        menuEntity.setDate(LocalDate.now());
        menuEntity.setRestaurant("Ресторант Бяла Река");

        ProductEntity p1 = new ProductEntity("Пастърва пълнена със зеленчуци", "Sfd", 400, new BigDecimal("11.50"), CategoryType.MAIN, menuEntity);
        ProductEntity p2 = new ProductEntity("Пастърва на скара", "Sfd", 330, new BigDecimal("10.50"), CategoryType.MAIN, menuEntity);
        ProductEntity p3 = new ProductEntity("Пастърва с моцарела", "Sfd", 260, new BigDecimal("8.80"), CategoryType.MAIN, menuEntity);
        ProductEntity p4 = new ProductEntity("Пица четири сезона", "Sfd", 1000, new BigDecimal("13.50"), CategoryType.MAIN, menuEntity);
        ProductEntity p5 = new ProductEntity("Пица Америка", "Sfd", 860, new BigDecimal("12.50"), CategoryType.MAIN, menuEntity);
        ProductEntity p6 = new ProductEntity("Спагети болонезе", "Sfd", 670, new BigDecimal("10.00"), CategoryType.SANDWICH, menuEntity);

        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addProduct(p3);
        productService.addProduct(p4);
        productService.addProduct(p5);
        productService.addProduct(p6);

        List<ProductEntity> allProducts = productService.getAllProducts();
        menuEntity.setProducts(allProducts);

        this.updateMenu(menuEntity);
    }

    public List<MenuEntity> getAllMenus() {
        return menuDAO.getAllMenus();
    }

    public MenuEntity getMenuById(Long id) {
        return menuDAO.getMenuById(id);
    }

    public void addMenu(MenuEntity menu) {
        menuDAO.addMenu(menu);
    }

    public void updateMenu(MenuEntity menu) {
        menuDAO.updateMenu(menu);
    }

    public void deleteMenu(Long id) {
        menuDAO.deleteMenu(id);
    }

    public List<ProductEntity> getProductsForMenu(Long menuId) {
        return menuDAO.getProductsForMenu(menuId);
    }

}
