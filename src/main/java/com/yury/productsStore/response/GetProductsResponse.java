package com.yury.productsStore.response;

import com.yury.productsStore.domain.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetProductsResponse extends BaseResponse {

    private List<Product> products = new ArrayList<>();
}
