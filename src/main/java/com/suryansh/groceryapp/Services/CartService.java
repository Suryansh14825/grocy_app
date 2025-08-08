package com.suryansh.groceryapp.Services;

import com.suryansh.groceryapp.Model.CartItem;
import com.suryansh.groceryapp.Repository.CartRepository;

import java.util.List;

public class CartService {

    private CartRepository cartRepository = new CartRepository();

    public boolean addToCart(CartItem item) {

        CartItem existingItem = cartRepository.getCartItem(item.getCustomerId(), item.getProductId());

        if (existingItem != null) {

            int newQuantity = existingItem.getQuantity() + item.getQuantity();
            return cartRepository.updateCartItem(item.getCustomerId(), item.getProductId(), newQuantity);
        } else {
            return cartRepository.addToCart(item);
        }
    }

    public List<CartItem> getCartItemsByCustomerId(int customerId) {
        return cartRepository.getCartItemsByCustomerId(customerId);
    }

    public boolean updateCartItemQuantity(int customerId, int productId, int newQuantity) {
        return cartRepository.updateCartItem(customerId, productId, newQuantity);
    }

    public boolean removeCartItem(int customerId, int productId) {
        return cartRepository.removeCartItem(customerId, productId);
    }

    public boolean clearCart(int customerId) {
        return cartRepository.clearCart(customerId);
    }
}

