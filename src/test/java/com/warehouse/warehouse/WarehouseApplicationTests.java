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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WarehouseApplicationTests {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	OrdersService ordersService;

	@Test
	@Order(1)
	void createProductAndCategory() {
		assertEquals(0,categoryService.getAll().size());
		assertEquals(0,productService.getAll().size());

		CategoryCreateDto firstCategory = new CategoryCreateDto();
		firstCategory.setName("FirstCat");
		CategoryCreateDto secondCategory = new CategoryCreateDto();
		secondCategory.setName("FirstCat");

		categoryService.addNew(firstCategory);
		categoryService.addNew(secondCategory);

		assertEquals(2,categoryService.getAll().size());

		ProductCreateDto firstProduct = new ProductCreateDto("First Product","L34","123321","Fregile",15, (long) 1);
		ProductCreateDto secondProduct = new ProductCreateDto("Second Product","L343","44334","Fregile",10, (long) 2);
		ProductCreateDto thirdProduct = new ProductCreateDto("Third Product","A14","9909","Fregile",10, (long) 2);

		productService.addNew(firstProduct);
		productService.addNew(secondProduct);
		productService.addNew(thirdProduct);

		assertEquals(3,productService.getAll().size());

	}
	@Test
	@Order(2)
	void findByCategoryId(){
		assertEquals(1,productService.getByCategory(1L).size());
		assertEquals(2,productService.getByCategory(2L).size());

	}

	@Test
	@Order(3)
	@Transactional
	void buyProductAndCreateOrder(){
		SoldProductDto soldProductDto = new SoldProductDto();
		soldProductDto.setProductId(4L);
		soldProductDto.setCount(8);
		productService.soldProduct(soldProductDto);

		assertEquals(1,ordersService.getAll().size());

		productService.addAmount(4L,10);

		assertEquals(12,productService.getById(4L).getCount());
		assertEquals(0,ordersService.getAll().size());

	}

	@Test
	@Order(4)
	void removeProduct(){
		productService.removeItem(4);
		assertEquals(2,productService.getAll().size());
		assertEquals(1,productService.getByCategory(2L).size());

	}
	@Test
	@Order(5)
	void removeCategory(){
		productService.removeItem(3);
		categoryService.removeById(1L);
		assertEquals(2,categoryService.getAll().size());

	}

}
