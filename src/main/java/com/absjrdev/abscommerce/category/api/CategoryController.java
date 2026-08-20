package com.absjrdev.abscommerce.category.api;

import com.absjrdev.abscommerce.category.application.CategoryService;
import com.absjrdev.abscommerce.category.domain.Category;
import com.absjrdev.abscommerce.category.dto.CategoryRequestDTO;
import com.absjrdev.abscommerce.category.dto.CategoryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@Tag(name = "Categories", description = "Operations related to categories")
public
class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(
            summary = "Retrieve all categories",
            description = "Retrieve a list of all registered categories"
    )
    @GetMapping
    public
    ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> list = categoryService.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok().body(list);
    }

       @Operation(
            summary = "Retrieve a category by ID",
            description = "Returns the details of a category identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public
    ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(toResponseDTO(category));
    }


    @Operation(
            summary = "Create a new category",
            description = "Creates a new category in the system"
    )
    @PostMapping
    public
    ResponseEntity<CategoryResponseDTO> insert(@RequestBody CategoryRequestDTO dto) {
        Category category = toEntity(dto);

        category = categoryService.insert(category);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(uri).body(toResponseDTO(category));
    }

    @Operation(
            summary = "Update an existing category",
            description = "Updates the information of an existing category."
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> update(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO dto) {

        Category category = toEntity(dto);

        category = categoryService.update(id, category);

        return ResponseEntity.ok().body(toResponseDTO(category));
    }

    @Operation(
            summary = "Delete a category",
            description = "Removes a category identified by the provided ID."
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private CategoryResponseDTO toResponseDTO(Category category) {

        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }


    private Category toEntity(CategoryRequestDTO dto) {

        return new Category(
                null,
                dto.name()
        );
    }

}

