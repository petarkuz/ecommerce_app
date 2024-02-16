package mk.finki.ukim.wp.repository;

import mk.finki.ukim.wp.model.Product;
import mk.finki.ukim.wp.model.ShoppingCart;
import mk.finki.ukim.wp.model.User;
import mk.finki.ukim.wp.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
