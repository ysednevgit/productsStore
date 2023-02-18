package com.yury.productsStore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @NotBlank
    private String productId;

    @NotNull
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return productId.equals(cartItem.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
