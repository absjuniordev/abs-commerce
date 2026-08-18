package com.absjrdev.abscommerce.product.application;

import com.absjrdev.abscommerce.exception.ResourceNotFoundException;
import com.absjrdev.abscommerce.product.domain.Product;
import com.absjrdev.abscommerce.product.repository.ProductRepository;
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
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Product insert(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found. id: " + id
                        )
                );
        updateProduct(entity, product);
        return productRepository.save(entity);
    }

    private void updateProduct(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImgUrl(product.getImgUrl());


    }
}
