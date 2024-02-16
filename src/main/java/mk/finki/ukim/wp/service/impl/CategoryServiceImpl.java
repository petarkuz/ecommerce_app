package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Category;
import mk.finki.ukim.wp.repository.CategoryRepository;
import mk.finki.ukim.wp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> save(String name, String description) {
        return Optional.of(this.categoryRepository.save(new Category(name, description)));
    }

    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }

}
