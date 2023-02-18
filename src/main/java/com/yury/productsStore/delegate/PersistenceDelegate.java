package com.yury.productsStore.delegate;

import com.yury.productsStore.repository.CartRepository;
import com.yury.productsStore.repository.ProductRepository;
import com.yury.productsStore.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class PersistenceDelegate {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;
}
