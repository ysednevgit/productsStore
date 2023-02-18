package com.yury.productsStore.unittests;

import com.yury.productsStore.delegate.PersistenceDelegate;
import com.yury.productsStore.delegate.ProductDelegate;
import com.yury.productsStore.domain.Cart;
import com.yury.productsStore.domain.Product;
import com.yury.productsStore.domain.User;
import com.yury.productsStore.repository.CartRepository;
import com.yury.productsStore.repository.ProductRepository;
import com.yury.productsStore.repository.UserRepository;
import com.yury.productsStore.request.CartItemRequest;
import com.yury.productsStore.response.AddToCartResponse;
import com.yury.productsStore.response.GetProductsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDelegateTests {

    @Mock
    PersistenceDelegate persistenceDelegate;

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    UserRepository userRepository;

    @Spy
    ProductDelegate delegate;

    @BeforeEach
    public void init() {
        delegate.setPersistenceDelegate(persistenceDelegate);

        when(persistenceDelegate.getProductRepository()).thenReturn(productRepository);
    }

    @Test
    public void getProducts_NormalFlow_ShouldReturnSomeProducts() {

        when(productRepository.findAll()).thenReturn(getProducts());

        GetProductsResponse  response = delegate.getProducts();

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
        Assertions.assertEquals(response.getProducts().size(), 1);
    }

    @Test
    public void getProduct_NormalFlow_ShouldReturnSomeProduct() {

        when(productRepository.findById(any())).thenReturn(Optional.of(getProducts().get(0)));

        GetProductsResponse  response = delegate.getProduct("1");

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
        Assertions.assertEquals(response.getProducts().size(), 1);
        Assertions.assertEquals(response.getProducts().get(0).getName(), "Product1");
    }

    @Test
    public void addToCart_NormalFlow_ShouldReturnCart() {

        when(persistenceDelegate.getCartRepository()).thenReturn(cartRepository);
        when(persistenceDelegate.getUserRepository()).thenReturn(userRepository);

        CartItemRequest request = new CartItemRequest();
        request.setUserId("1");
        request.setQuantity(1);

        Cart cart = new Cart();

        when(productRepository.findById(any())).thenReturn(Optional.of(getProducts().get(0)));
        when(userRepository.findById(any())).thenReturn(Optional.of(getUsers().get(0)));
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart));

        AddToCartResponse response = delegate.addToCart("1", request);

        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
        Assertions.assertNotNull(response.getCart());

    }

    private List<User> getUsers() {
        User user = new User("1", "Mike Mikeson");

        List<User> users = new ArrayList<>();
        users.add(user);

        return users;
    }

    private List<Product> getProducts() {

        Product product = new Product();
        product.setId("1");
        product.setName("Product1");
        product.setDescription("Product1 description");
        product.setPrice(BigDecimal.valueOf(10));

        List<Product> products = new ArrayList<>();
        products.add(product);

        return products;
    }


}
