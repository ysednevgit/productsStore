package com.yury.productsStore.integrationtests;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductsStoreApplicationTests {

    @Test
    void testMainControllerGetProducts() {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/products",
                HttpMethod.GET, entity, String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        String responseBody = response.getBody();
        String status = JsonPath.parse(responseBody).read("$.status");
        Assertions.assertEquals(status, "OK");
    }

    @Test
    void testMainControllerGetProduct() {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = new RestTemplate().exchange("http://localhost:8080/products/1",
                HttpMethod.GET, entity, String.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        String responseBody = response.getBody();
        String status = JsonPath.parse(responseBody).read("$.status");
        Assertions.assertEquals(status, "OK");
    }

}
