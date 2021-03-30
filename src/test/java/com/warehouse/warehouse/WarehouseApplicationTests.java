package com.warehouse.warehouse;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.service.CategoryService;
import com.warehouse.warehouse.service.OrdersService;
import com.warehouse.warehouse.service.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WarehouseApplicationTests {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    TestRestTemplate restTemplate;

    static Product productOne;
	static Product productTwo;
	static Product productThree;
	static Product productFour;
	static Product productFive;



    @Test
    @Order(1)
    void createProductAndCategory() {
        assertEquals(2, categoryService.getAll().size());
        assertEquals(1, productService.getAll().size());

        CategoryCreateDto firstCategory = new CategoryCreateDto();
        firstCategory.setName("FirstCat");
        CategoryCreateDto secondCategory = new CategoryCreateDto();
        secondCategory.setName("FirstCat");

        categoryService.addNew(firstCategory);
        categoryService.addNew(secondCategory);

        assertEquals(4, categoryService.getAll().size());

        ProductCreateDto firstProduct = new ProductCreateDto("First Product", "L34", "123321", "Fregile", 15, (long) 1);
        ProductCreateDto secondProduct = new ProductCreateDto("Second Product", "L343", "44334", "Fregile", 10, (long) 2);
        ProductCreateDto thirdProduct = new ProductCreateDto("Third Product", "A14", "9909", "Fregile", 10, (long) 2);

        productOne = productService.addNew(firstProduct);
        productTwo = productService.addNew(secondProduct);
        productThree=productService.addNew(thirdProduct);

        assertEquals(4, productService.getAll().size());

    }

    @Test
    @Order(2)
    void createProduct() {
        ProductCreateDto request = new ProductCreateDto("Table", "L231", "0099898", "Fregile", 13, 1L);
        ProductCreateDto secondRequest = new ProductCreateDto("Table wooden", "L231", "0099898", "Fregile", 15, 1L);
        ResponseEntity responseEntity = restTemplate.postForEntity("/product", request, ProductCreateDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ResponseEntity secondResponseEntity = restTemplate.postForEntity("/product", secondRequest, ProductCreateDto.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    @Order(3)
    void restGetProducts() {
        ResponseEntity<ProductCreateDto[]> responseEntity = restTemplate.getForEntity("/product", ProductCreateDto[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(6, responseEntity.getBody().length);

    }



}
