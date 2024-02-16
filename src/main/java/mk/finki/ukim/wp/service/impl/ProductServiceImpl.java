package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Category;
import mk.finki.ukim.wp.model.Manufacturer;
import mk.finki.ukim.wp.model.Product;
import mk.finki.ukim.wp.model.dto.ProductDto;
import mk.finki.ukim.wp.model.exceptions.CategoryNotFoundException;
import mk.finki.ukim.wp.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wp.model.exceptions.ProductNotFoundException;
import mk.finki.ukim.wp.repository.CategoryRepository;
import mk.finki.ukim.wp.repository.ManufacturerRepository;
import mk.finki.ukim.wp.repository.ProductRepository;
import mk.finki.ukim.wp.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Product> save(ProductDto productDto) {
        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));

        this.productRepository.deleteByName(productDto.getName());

        Product product = new Product(productDto.getName(), productDto.getQuantity(), productDto.getPrice(), manufacturer, category);
        this.productRepository.save(product);

        return Optional.of(product);
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));
        product.setManufacturer(manufacturer);

        this.productRepository.save(product);
        return Optional.of(product);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
