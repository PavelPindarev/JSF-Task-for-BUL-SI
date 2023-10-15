package bg.bulsi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menues")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuEntity extends BaseEntity {

    private LocalDate date;

    private String restaurant;

    @OneToMany(mappedBy = "menu", targetEntity = ProductEntity.class, fetch = FetchType.EAGER)
    private List<ProductEntity> products;

    public MenuEntity(List<ProductEntity> products, String restaurant) {
        this.products = products;
        this.restaurant = restaurant;
        this.date = LocalDate.now();
    }
}
