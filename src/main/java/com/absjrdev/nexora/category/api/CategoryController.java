package com.absjrdev.nexora.category.api;

import com.absjrdev.nexora.category.application.CategoryService;
import com.absjrdev.nexora.category.domain.Category;
import com.absjrdev.nexora.order.application.OrderService;
import com.absjrdev.nexora.order.domain.Order;
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
}
