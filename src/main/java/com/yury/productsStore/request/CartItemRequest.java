package com.yury.productsStore.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CartItemRequest {

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotBlank
    private String userId;
}