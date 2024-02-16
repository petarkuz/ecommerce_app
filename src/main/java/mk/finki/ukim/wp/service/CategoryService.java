package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Optional<Category> save(String name, String description);
    void deleteById(Long id);
}
