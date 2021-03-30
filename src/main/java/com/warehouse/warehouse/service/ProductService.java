package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.dto.SoldProductDto;
import com.warehouse.warehouse.model.Product;

import java.util.List;

public interface ProductService {
    /**
     * method that save new Product to database with auto-generated ID and linked to the created Category.
     * return Product with generated value for ID
     */
    Product addNew(ProductCreateDto productCreateDto);
    /**
     * method that allow to increase value of specific Product.
     * Entry contains specific productId which corresponding with Id in Database for that product
     * and int amount that define increased value for that product.
     * return Product with new updated value for amount.
     */
    Product addAmount(Long productId, int amount);
    /**
     * method return all product stored in the database.
     * return List<Product>
     */
    List<Product> getAll();
    /**
     * method that remove product in database by ID which corresponds with entered value.
     */
    void removeItem(long itemId);
    /**
     * method return all product by specific Category. Category is defined by Category ID.
     * System allow multiple records of product for one CategoryId.
     * return List<Product>
     */
    List<Product> getByCategory(Long categoryId);
    /**
     * method update existing Product record in the database. Value for this product find by ID is overriden
     * by new value and store in database with old ID for the product.
     * return Product with new updated values.
     */
    Product updateProduct(Long productId, ProductCreateDto productCreateDto);
    /**
     * method that manage sold of product Entry of this method is SoldProductDto that include productId to find
     * product in the database and amount to decrease amount from finded product by ID.
     * method is done in case that amount in entry in less than amount for Product in Database.
     * If is amount after the method was done less than configured value is called method needToBeOrder();
     */
    void soldProduct(SoldProductDto soldProduct);
    /**
     * method return specific Product by ID stored in the database.
     * return Product
     */
    Product getById(Long productId);
}
