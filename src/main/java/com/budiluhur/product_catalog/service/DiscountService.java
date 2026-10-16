package com.budiluhur.product_catalog.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double calculateDiscountedPrice(double originalPrice, double discountPercentage) {
        double discountAmount = originalPrice * (discountPercentage / 100);
        return originalPrice - discountAmount;
    }
}