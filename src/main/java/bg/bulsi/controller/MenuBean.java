package bg.bulsi.controller;

import bg.bulsi.model.entity.MenuEntity;
import bg.bulsi.model.entity.ProductEntity;
import bg.bulsi.service.MenuService;
import bg.bulsi.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Named(value = "menuBean")
@RequestScoped
public class MenuBean implements Serializable {

    @Inject
    private MenuService menuService;
    @Inject
    private ProductService productService;

    private MenuEntity selectedMenu;
    private List<ProductEntity> productsForSelectedMenu;
    private ProductEntity newProduct;


    @PostConstruct
    private void loadMenus() {
        this.newProduct = new ProductEntity();
        this.selectedMenu = this.menuService.getMenuById(1L);
        this.productsForSelectedMenu = this.selectedMenu.getProducts();
    }

    public void addProductToMenu(ProductEntity product) {
        product.setMenu(this.selectedMenu);
        productService.addProduct(product);
        this.selectedMenu.getProducts().add(product);
        menuService.updateMenu(this.selectedMenu);
        this.newProduct = new ProductEntity();
    }

    public void deleteProduct(ProductEntity product) {
        this.selectedMenu.getProducts().remove(product);
        this.productService.deleteProduct(product.getId());
    }

    public void saveProduct() {
        productService.updateProduct(newProduct.getId(), newProduct);
    }
}
