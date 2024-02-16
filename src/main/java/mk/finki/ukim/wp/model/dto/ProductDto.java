package mk.finki.ukim.wp.model.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;

    private Integer quantity;

    private Double price;

    private Long manufacturer;

    private Long category;

    public ProductDto(String name, Integer quantity, Double price, Long manufacturer, Long category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category = category;
    }
}
