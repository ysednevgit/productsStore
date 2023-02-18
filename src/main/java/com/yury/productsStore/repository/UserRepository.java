package com.yury.productsStore.repository;

import com.yury.productsStore.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
