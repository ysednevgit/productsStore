package com.yury.productsStore.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

}
