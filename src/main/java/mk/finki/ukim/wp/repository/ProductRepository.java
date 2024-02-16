package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteByName(String name);
    Optional<Product> findByName(String name);
}
