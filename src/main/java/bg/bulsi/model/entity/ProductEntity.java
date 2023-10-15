package bg.bulsi.model.entity;

import bg.bulsi.model.enums.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity extends BaseEntity {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double weight;


    private BigDecimal price;

    @Column(name = "category_type")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @ManyToOne(targetEntity = MenuEntity.class)
    private MenuEntity menu;

}
