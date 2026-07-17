package com.absjrdev.nexora.product.api;

import com.absjrdev.nexora.product.application.ProductService;
import com.absjrdev.nexora.product.domain.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Retrieve all products",
            description = "Retrieve a list of all registered products"
    )
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @Operation(
            summary = "Retrieve a product by ID",
            description = "Returns the details of a product identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }
}
