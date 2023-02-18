package com.yury.productsStore.response;

import com.yury.productsStore.domain.Cart;
import lombok.Data;

@Data
public class AddToCartResponse extends BaseResponse {

    private Cart cart;
}
