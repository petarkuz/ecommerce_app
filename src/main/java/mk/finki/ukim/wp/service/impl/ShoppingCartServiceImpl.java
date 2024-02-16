package mk.finki.ukim.wp.service.impl;

import mk.finki.ukim.wp.model.Product;
import mk.finki.ukim.wp.model.ShoppingCart;
import mk.finki.ukim.wp.model.User;
import mk.finki.ukim.wp.model.enumerations.ShoppingCartStatus;
import mk.finki.ukim.wp.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.finki.ukim.wp.model.exceptions.ProductNotFoundException;
import mk.finki.ukim.wp.model.exceptions.UserNotFoundException;
import mk.finki.ukim.wp.repository.ProductRepository;
import mk.finki.ukim.wp.repository.ShoppingCartRepository;
import mk.finki.ukim.wp.repository.UserRepository;
import mk.finki.ukim.wp.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if (shoppingCart.getProducts().stream().anyMatch(item -> item.getId().equals(productId)))
            throw new ProductAlreadyInShoppingCartException(productId, username);

        shoppingCart.getProducts().add(product);

        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (this.shoppingCartRepository.findById(cartId).isEmpty())
            throw new ProductNotFoundException(cartId);

        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }
}
