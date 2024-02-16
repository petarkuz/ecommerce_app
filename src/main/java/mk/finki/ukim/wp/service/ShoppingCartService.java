package mk.finki.ukim.wp.service;

import mk.finki.ukim.wp.model.Product;
import mk.finki.ukim.wp.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart addProductToShoppingCart(String username, Long productId);

    ShoppingCart getActiveShoppingCart(String username);

    List<Product> listAllProductsInShoppingCart(Long cartId);

}
