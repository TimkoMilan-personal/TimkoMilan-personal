package com.warehouse.warehouse.service;

import com.warehouse.warehouse.dto.ProductCreateDto;
import com.warehouse.warehouse.dto.SoldProductDto;
import com.warehouse.warehouse.model.Category;
import com.warehouse.warehouse.model.Orders;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CategoryRepository;
import com.warehouse.warehouse.repository.OrderRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import com.warehouse.warehouse.util.ProductUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final OrdersService ordersService;
    private final ProductUtil productUtil;

    public ProductServiceImpl(ProductRepository productRepository, @Lazy CategoryService categoryService, OrdersService ordersService, ProductUtil productUtil) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.ordersService = ordersService;
        this.productUtil = productUtil;
    }


    @Override
    public void addNew(ProductCreateDto productCreateDto) {
        Category category = categoryService.findById(productCreateDto.getCategoryId());
        Product product = productUtil.fromDtoToObject(productCreateDto);
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public void addAmount(Long productId, int amount) {
        Product product = productRepository.getOne(productId);
        product.setCount(product.getCount() + amount);
        productRepository.save(product);
        List orders = ordersService.findByProductId(productId);
        if (orders.size() != 0) {
            ordersService.removeOrderById(productId);
        }
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void removeItem(long itemId) {
        productRepository.deleteById(itemId);
    }

    @Override
    public List<Product> getByCategory(Long categoryId) {
        return productRepository.findProductsByCategoryId(categoryId);
    }

    @Override
    public Product updateProduct(Long productId, ProductCreateDto productCreateDto) {
//        Optional<Product> products = productRepository.findById(productId);
        return null;
    }

    @Override
    public void soldProduct(SoldProductDto soldProduct) {
        Product product = productRepository.getOne(soldProduct.getProductId());
        if (isProductAvailable(soldProduct.getProductId(), soldProduct.getCount())) {
            product.setCount(product.getCount() - soldProduct.getCount());
            productRepository.save(product);
        }
        needsToBeOrdered(product, soldProduct);
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.getOne(productId);
    }

    private boolean isProductAvailable(Long productId, int count) {
        Product product = productRepository.getOne(productId);
        return product.getCount() > 0 && product.getCount() >= count;
    }

    private boolean needsToBeOrdered(Product product, SoldProductDto productDto) {
        // TODO changed to value from properties file/ Check if record will not be duplicated
        if (product.getCount() < 5 && !isInListOrders(productDto.getProductId())) {

            Date date = new Date();
            Orders orders = new Orders();

            orders.setProductId(productDto.getProductId());
            orders.setCreateRecordDate(date);
            orders.setOrdered(false);
            ordersService.saveOrder(orders);
            return true;
        } else {
            return false;
        }
    }

    private boolean isInListOrders(Long productId) {
        List<Orders> ordersList = ordersService.findByProductId(productId);
        return ordersList.size() != 0;
    }

}
