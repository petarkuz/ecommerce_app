package mk.finki.ukim.wp.web.rest;


import mk.finki.ukim.wp.model.Product;
import mk.finki.ukim.wp.model.ShoppingCart;
import mk.finki.ukim.wp.model.User;
import mk.finki.ukim.wp.model.dto.ShoppingCartDto;
import mk.finki.ukim.wp.service.ShoppingCartService;
import mk.finki.ukim.wp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping
    public List<Product> getShoppingCartProducts(HttpServletRequest req) {
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        return this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId());
    }

    @PostMapping("/add-product/{id}")
    public ShoppingCartDto addProductToShoppingCart(@PathVariable Long id, Authentication authentication) {
        //User user = (User) authentication.getPrincipal();
        //return this.shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
        ShoppingCart cart = this.shoppingCartService.addProductToShoppingCart((String) authentication.getPrincipal(), id);
        return new ShoppingCartDto(cart.getId(), cart.getCreationTime(), cart.getProducts(), cart.getStatus());
    }
}
