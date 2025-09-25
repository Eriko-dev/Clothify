package com.example.clothify.service;

import com.example.clothify.entity.Product;
import com.example.clothify.entity.ShoppingCart;
import com.example.clothify.entity.User;
import com.example.clothify.repository.CartRepository;
import com.example.clothify.repository.ProductRepository;
import com.example.clothify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<ShoppingCart> getCartByUserID(Integer userId) {
        return cartRepository.findByUser_Id(userId);
    }


    public ShoppingCart addToCart(ShoppingCart cartItem) {
        User user = userRepository.findById(cartItem.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(cartItem.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cartItem.setUser(user);
        cartItem.setProduct(product);

        return cartRepository.save(cartItem);
    }

    public ShoppingCart updateQuantity(Integer cartId, Integer quantity) {
        ShoppingCart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }
    public void removeItem(Integer cartId) {
        cartRepository.deleteById(cartId);
    }
    public void clearCart(User user) {
        List<ShoppingCart> carts = cartRepository.findByUser_Id(user.getId());
        cartRepository.deleteAll(carts);
    }
}

