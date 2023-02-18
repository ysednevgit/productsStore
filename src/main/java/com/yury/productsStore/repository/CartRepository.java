package com.yury.productsStore.repository;

import com.yury.productsStore.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

}
