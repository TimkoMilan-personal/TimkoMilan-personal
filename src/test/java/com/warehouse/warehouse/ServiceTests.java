package com.warehouse.warehouse;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.dto.SoldProductDto;
import com.warehouse.warehouse.model.Category;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.service.CategoryService;
import com.warehouse.warehouse.service.OrdersService;
import com.warehouse.warehouse.service.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTests {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrdersService ordersService;

    static Category categoryOne;
    static Category categoryTwo;

    static Product productOne;
    static Product productTwo;
    static Product productThree;
    static Product productFour;
    static Product productFive;

    @Test
    @Order(1)
    void createProductAndCategory() {
        assertEquals(0, categoryService.getAll().size());
        assertEquals(0, productService.getAll().size());

        CategoryCreateDto firstCategory = new CategoryCreateDto();
        firstCategory.setName("FirstCat");
        CategoryCreateDto secondCategory = new CategoryCreateDto();
        secondCategory.setName("FirstCat");

        categoryOne=categoryService.addNew(firstCategory);
        categoryTwo=categoryService.addNew(secondCategory);

        assertEquals(2, categoryService.getAll().size());

        ProductCreateDto firstProduct = new ProductCreateDto("First Product", "L34", "123321", "Fregile", 10, (long) 1);
        ProductCreateDto secondProduct = new ProductCreateDto("Second Product", "L343", "44334", "Fregile", 10, (long)2);
        ProductCreateDto thirdProduct = new ProductCreateDto("Third Product", "A14", "9909", "Fregile", 10, (long) 2);

        productOne = productService.addNew(firstProduct);
        productTwo = productService.addNew(secondProduct);
        productThree=productService.addNew(thirdProduct);

        assertEquals(3, productService.getAll().size());

    }
    @Test
    @Order(2)
    void findByCategoryId() {
        assertEquals(1, productService.getByCategory(categoryOne.getId()).size());
        assertEquals(2, productService.getByCategory(categoryTwo.getId()).size());

    }

    @Test
    @Order(3)
    @Transactional
    void buyProductAndCreateOrder() {
        SoldProductDto soldProductDto = new SoldProductDto();
        soldProductDto.setProductId(productOne.getId());
        soldProductDto.setCount(8);
        productService.soldProduct(soldProductDto);

        assertEquals(1, ordersService.getAll().size());

        productService.addAmount(productOne.getId(), 10);

        assertEquals(12, productService.getById(productOne.getId()).getCount());
        assertEquals(0, ordersService.getAll().size());

    }

    @Test
    @Order(4)
    void removeProduct() {
        productService.removeItem(productTwo.getId());
        assertEquals(2, productService.getAll().size());
        assertEquals(1, productService.getByCategory(categoryTwo.getId()).size());

    }

    @Test
    @Order(5)
    void removeCategory() {
        productService.removeItem(productOne.getId());
        categoryService.removeById(categoryOne.getId());
        assertEquals(2, categoryService.getAll().size());

    }
}
