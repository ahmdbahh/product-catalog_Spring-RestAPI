package com.budiluhur.product_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.budiluhur.product_catalog.exception.ProductNotFoundException;
import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DiscountService discountService;

    // constructor injection
    public ProductService(ProductRepository productRepository, DiscountService discountService) {
        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) throws ProductNotFoundException {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        productRepository.addProduct(product);
        return product;
    }

    public double getDiscountedProductPrice(String id, double discount) throws ProductNotFoundException {
        Product product = productRepository.findById(id);
        return discountService.calculateDiscountedPrice(product.getPrice(), discount);
    }
    
    public Product updateProduct(String id, Product product)
            throws ProductNotFoundException {
        return productRepository.updateProduct(id, product);
    }

    public boolean deleteProduct(String id)
            throws ProductNotFoundException {
        return productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepository.searchByName(keyword);
    }
}