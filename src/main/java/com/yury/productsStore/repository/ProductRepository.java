package com.yury.productsStore.repository;

import com.yury.productsStore.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}

