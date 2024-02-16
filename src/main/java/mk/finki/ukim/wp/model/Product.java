package mk.finki.ukim.wp.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    private Double price;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String name, Integer quantity, Double price, Manufacturer manufacturer, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category = category;
    }
}
