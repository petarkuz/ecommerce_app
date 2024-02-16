package mk.finki.ukim.wp.model.dto;

import lombok.Data;
import mk.finki.ukim.wp.model.Product;
import mk.finki.ukim.wp.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShoppingCartDto {
    private Long id;
    private LocalDateTime creationTime;
    private List<Product> products;
    private ShoppingCartStatus status;

    public ShoppingCartDto(Long id, LocalDateTime creationTime, List<Product> products, ShoppingCartStatus status) {
        this.id = id;
        this.creationTime = creationTime;
        this.products = products;
        this.status = status;
    }
}
