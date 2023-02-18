package com.yury.productsStore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String name;

    private String cartId = UUID.randomUUID().toString();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}