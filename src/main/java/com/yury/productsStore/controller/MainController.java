package com.yury.productsStore.controller;

import com.yury.productsStore.delegate.ProductDelegate;
import com.yury.productsStore.domain.Cart;
import com.yury.productsStore.request.CartItemRequest;
import com.yury.productsStore.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class MainController {

    @Autowired
    private ProductDelegate productDelegate;

    @GetMapping
    public ResponseEntity<BaseResponse> getAllProducts() {

        BaseResponse response = productDelegate.getProducts();

        return new ResponseEntity(response, response.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable String id) {

        BaseResponse response = productDelegate.getProduct(id);

        return new ResponseEntity(response, response.getStatus());
    }

    @PostMapping("/{id}/cart")
    public ResponseEntity<BaseResponse> addToCart(@PathVariable String id, @RequestBody @Valid CartItemRequest cartItemRequest) {

        BaseResponse response = productDelegate.addToCart(id, cartItemRequest);

        return new ResponseEntity(response, response.getStatus());
    }


}