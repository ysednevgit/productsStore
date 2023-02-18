package com.yury.productsStore.domain;

import com.yury.productsStore.pojo.CartItem;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
@Data
public class Cart {

    @Id
    private String id;

    private List<CartItem> items = new ArrayList<>();

}