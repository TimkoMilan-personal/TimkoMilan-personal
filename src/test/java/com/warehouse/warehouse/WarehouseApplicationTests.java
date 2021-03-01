package com.warehouse.warehouse;

import com.warehouse.warehouse.dto.CategoryCreateDto;
import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.dto.SoldProductDto;
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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Test
	@Order(1)
	void createProductAndCategory() {
		assertEquals(0, categoryService.getAll().size());
		assertEquals(0, productService.getAll().size());

		CategoryCreateDto firstCategory = new CategoryCreateDto();
		firstCategory.setName("FirstCat");
		CategoryCreateDto secondCategory = new CategoryCreateDto();
		secondCategory.setName("FirstCat");

		categoryService.addNew(firstCategory);
		categoryService.addNew(secondCategory);

		assertEquals(2, categoryService.getAll().size());

		ProductCreateDto firstProduct = new ProductCreateDto("First Product", "L34", "123321", "Fregile", 15, (long) 1);
		ProductCreateDto secondProduct = new ProductCreateDto("Second Product", "L343", "44334", "Fregile", 10, (long) 2);
		ProductCreateDto thirdProduct = new ProductCreateDto("Third Product", "A14", "9909", "Fregile", 10, (long) 2);

		productService.addNew(firstProduct);
		productService.addNew(secondProduct);
		productService.addNew(thirdProduct);

		assertEquals(3, productService.getAll().size());

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

        System.out.println(responseEntity.toString());
    }

	@Test
	void restGetProducts() {
		ResponseEntity<ProductCreateDto[]> responseEntity = restTemplate.getForEntity("/product", ProductCreateDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(5, responseEntity.getBody().length);
	}

//	@Test
//	@Order()
//	void deleteFirstUser() {
//		restTemplate.delete("/product");
//		//check if user deleted
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("itemId", "2");
//
//		ResponseEntity<ProductCreateDto[]> responseEntity = restTemplate.getForEntity( "/services/users",params, ProductCreateDto[].class);
//		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//		assertEquals(1, responseEntity.getBody().length);
//	}

//	@Test
//	@Order(2)
//	void findByCategoryId(){
//		assertEquals(1,productService.getByCategory(1L).size());
//		assertEquals(2,productService.getByCategory(2L).size());
//
//	}
//
//	@Test
//	@Order(3)
//	@Transactional
//	void buyProductAndCreateOrder(){
//		SoldProductDto soldProductDto = new SoldProductDto();
//		soldProductDto.setProductId(4L);
//		soldProductDto.setCount(8);
//		productService.soldProduct(soldProductDto);
//
//		assertEquals(1,ordersService.getAll().size());
//
//		productService.addAmount(4L,10);
//
//		assertEquals(12,productService.getById(4L).getCount());
//		assertEquals(0,ordersService.getAll().size());
//
//	}
//
//	@Test
//	@Order(4)
//	void removeProduct(){
//		productService.removeItem(4);
//		assertEquals(2,productService.getAll().size());
//		assertEquals(1,productService.getByCategory(2L).size());
//
//	}
//	@Test
//	@Order(5)
//	void removeCategory(){
//		productService.removeItem(3);
//		categoryService.removeById(1L);
//		assertEquals(2,categoryService.getAll().size());
//
//	}

}
