package mk.finki.ukim.wp.model;

import javax.persistence.*;
import lombok.Data;
import mk.finki.ukim.wp.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime creationTime;

    @ManyToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
        this.creationTime = LocalDateTime.now();
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
