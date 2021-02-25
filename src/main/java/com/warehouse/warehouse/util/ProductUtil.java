package com.warehouse.warehouse.util;

import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductUtil {

    public Product fromDtoToObject(ProductCreateDto productCreateDto){
        Product product = new Product();
        product.setIdCode(productCreateDto.getIdCode());
        product.setPlace(productCreateDto.getPlace());
        product.setName(productCreateDto.getName());
        product.setNote(product.getNote());
        product.setCount(productCreateDto.getCount());
        return product;
    }


}
