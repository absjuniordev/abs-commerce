package com.absjrdev.nexora.product.application;

import com.absjrdev.nexora.product.domain.Product;
import com.absjrdev.nexora.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
}
