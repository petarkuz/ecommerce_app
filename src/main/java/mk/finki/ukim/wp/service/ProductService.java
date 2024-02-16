package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Product;
import mk.finki.ukim.wp.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> save(ProductDto productDto);

    Optional<Product> edit(Long id, ProductDto productDto);

    void deleteById(Long id);
}
