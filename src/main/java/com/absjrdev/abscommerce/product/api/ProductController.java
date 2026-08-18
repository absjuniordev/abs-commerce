package com.absjrdev.abscommerce.product.api;

import com.absjrdev.abscommerce.product.application.ProductService;
import com.absjrdev.abscommerce.product.domain.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @Operation(
            summary = "Create a new Product",
            description = "Creates a new Product in the system."
    )
    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj) {
        obj = productService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @Operation(
            summary = "Delete a Product",
            description = "Removes a Product identified by the provided ID from the system."
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update an existing Product",
            description = "Updates the information of an existing Product identified by the provided ID."
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product obj) {
        obj = productService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
