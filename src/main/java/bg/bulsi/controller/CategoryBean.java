package bg.bulsi.controller;

import bg.bulsi.model.enums.CategoryType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Named
@ApplicationScoped
public class CategoryBean implements Serializable {
    private final CategoryType[] categories;

    public CategoryBean() {
        categories = CategoryType.values();
    }

}