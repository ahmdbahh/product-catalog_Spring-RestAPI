package com.budiluhur.product_catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.budiluhur.product_catalog.exception.ProductNotFoundException;
import com.budiluhur.product_catalog.model.Product;
import com.budiluhur.product_catalog.service.ProductService;

@SpringBootApplication
public class ProductCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductService productService) {
        return args -> {
            System.out.println("=== SPRING BOOT PRODUCT CATALOG IS RUNNING ===");
            productService.createProduct(
                    new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));

            System.out.println("Daftar Produk di Spring Container:");
            productService.getAllProducts()
                    .forEach(System.out::println);

            System.out.println();
            System.out.println("--- INFORMASI HARGA & DISKON ---");
            printDiscountInfo(productService, "PRD-01", 10.0);
            printDiscountInfo(productService, "PRD-02", 0.0);
        };
    }

    private void printDiscountInfo(ProductService productService, String id, double discount) {
        try {
            Product product = productService.getProductById(id);
            double finalPrice = productService.getDiscountedProductPrice(id, discount);
            System.out.println(product.getId() + " " + product.getName());
            System.out.printf("- Harga Normal : Rp%,.2f%n", product.getPrice());
            if (discount > 0) {
                System.out.println("- Diskon       : " + discount + "% (Ada Diskon)");
            } else {
                System.out.println("- Diskon       : 0% (Tidak Ada Diskon)");
            }
            System.out.printf("- Harga Akhir  : Rp%,.2f%n%n", finalPrice);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}