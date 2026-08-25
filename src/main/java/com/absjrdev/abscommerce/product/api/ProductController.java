package com.absjrdev.abscommerce.product.api;

import com.absjrdev.abscommerce.category.dto.CategoryResponseDTO;
import com.absjrdev.abscommerce.product.application.ProductService;
import com.absjrdev.abscommerce.product.domain.Product;
import com.absjrdev.abscommerce.product.dto.ProductRequestDTO;
import com.absjrdev.abscommerce.product.dto.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> list = productService.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok().body(list);
    }


    @Operation(
            summary = "Retrieve a product by ID",
            description = "Returns the details of a product identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(toResponseDTO(product));
    }

    @Operation(
            summary = "Create a new Product",
            description = "Creates a new Product in the system."
    )
    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductRequestDTO dto) {
        Product product = toEntity(dto);

        product = productService.insert(product);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).body(toResponseDTO(product));
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
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
       Product product = toEntity(dto);

       product = productService.update(id, product);

        return ResponseEntity.ok().body(toResponseDTO(product));
    }

    private ProductResponseDTO toResponseDTO(Product product) {

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getCategories()
                        .stream()
                        .map(CategoryResponseDTO::new)
                        .collect(Collectors.toSet())

        );

    }

    private Product toEntity(ProductRequestDTO dto) {

        return new Product(
                null,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.imgUrl()
        );
    }

}
