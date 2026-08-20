package com.absjrdev.abscommerce.category.api;

import com.absjrdev.abscommerce.category.application.CategoryService;
import com.absjrdev.abscommerce.category.domain.Category;
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
    ResponseEntity<List<Category>> findAll() {
        List<Category> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @Operation(
            summary = "Retrieve a category by ID",
            description = "Returns the details of a category identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public
    ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }


    @Operation(
            summary = "Create a new category",
            description = "Creates a new category in the system"
    )
    @PostMapping
    public
    ResponseEntity<Category> insert(@RequestBody Category category) {
        Category entity = categoryService.insert(category);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(uri).body(entity);
    }

    @Operation(
            summary = "Update an existing category",
            description = "Updates the information of an existing category."
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(
            @PathVariable Long id,
            @RequestBody Category category) {

        category = categoryService.update(id, category);

        return ResponseEntity.ok().body(category);
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
}

