package com.yury.productsStore.delegate;

import com.yury.productsStore.domain.Cart;
import com.yury.productsStore.domain.Product;
import com.yury.productsStore.domain.User;
import com.yury.productsStore.exception.ProductNotFoundException;
import com.yury.productsStore.exception.UserNotFoundException;
import com.yury.productsStore.pojo.CartItem;
import com.yury.productsStore.request.CartItemRequest;
import com.yury.productsStore.response.AddToCartResponse;
import com.yury.productsStore.response.GetProductsResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ProductDelegate {

    @Autowired
    private PersistenceDelegate persistenceDelegate;

    public GetProductsResponse getProducts() {

        GetProductsResponse response = new GetProductsResponse();

        response.setProducts(persistenceDelegate.getProductRepository().findAll());

        response.setStatus(HttpStatus.OK);

        return response;
    }

    public GetProductsResponse getProduct(String id) {

        GetProductsResponse response = new GetProductsResponse();

        List<Product> products = new ArrayList<>();

        products.add(getProductById(id));

        response.setProducts(products);

        response.setStatus(HttpStatus.OK);

        return response;
    }

    public AddToCartResponse addToCart(String productId, CartItemRequest cartItemRequest) {

        AddToCartResponse response = new AddToCartResponse();

        Product product = getProductById(productId);

        User user = getUserById(cartItemRequest.getUserId());

        Cart cart = getOrCreateCart(user.getCartId());

        int quantity = cartItemRequest.getQuantity();

        CartItem cartItem = new CartItem(product.getId(), quantity);

        if (cart.getItems().contains(cartItem)) {
            CartItem existingCartItem = cart.getItems().get(cart.getItems().indexOf(cartItem));

            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
        } else {
            cart.getItems().add(cartItem);
        }

        persistenceDelegate.getCartRepository().save(cart);

        response.setCart(cart);

        response.setStatus(HttpStatus.OK);

        return response;
    }

    private Cart getOrCreateCart(String cartId) {
        Optional<Cart> optionalCart = persistenceDelegate.getCartRepository().findById(cartId);
        if (optionalCart.isPresent()) {
            return optionalCart.get();
        }

        Cart cart = new Cart();
        cart.setId(cartId);

        return persistenceDelegate.getCartRepository().save(cart);
    }

    private Product getProductById(String id) {
        Optional<Product> optionalProduct = persistenceDelegate.getProductRepository().findById(id);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();

        } else {
            throw new ProductNotFoundException(id);
        }
    }

    private User getUserById(String id) {
        Optional<User> optionalUser = persistenceDelegate.getUserRepository().findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();

        } else {
            throw new UserNotFoundException(id);
        }
    }

}
