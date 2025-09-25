package com.example.clothify.controller;

import com.example.clothify.entity.ShoppingCart;
import com.example.clothify.entity.User;
import com.example.clothify.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    @Autowired
    private CartService shoppingCartService;

    @GetMapping("/{userId}")
    public List<ShoppingCart> getCartByUser(@PathVariable Integer userId) {
        return shoppingCartService.getCartByUserID(userId);
    }

    @PostMapping("/add")
    public ShoppingCart addToCart(@RequestBody ShoppingCart cartItem) {
        return shoppingCartService.addToCart(cartItem);
    }


    @PutMapping("/{cartId}")
    public ShoppingCart updateQuantity(@PathVariable Integer cartId,
                                       @RequestParam int quantity) {
        return shoppingCartService.updateQuantity(cartId, quantity);
    }

    @DeleteMapping("/{cartId}")
    public void removeItem(@PathVariable Integer cartId) {
        shoppingCartService.removeItem(cartId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Integer userId, @RequestBody User user) {
        shoppingCartService.clearCart(user);
    }
}
