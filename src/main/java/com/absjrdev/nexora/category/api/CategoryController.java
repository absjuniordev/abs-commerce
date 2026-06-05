package com.absjrdev.nexora.category.api;

import com.absjrdev.nexora.category.application.CategoryService;
import com.absjrdev.nexora.category.domain.Category;
import com.absjrdev.nexora.order.application.OrderService;
import com.absjrdev.nexora.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public
class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public
    ResponseEntity<List<Category>> findAll() {
        List<Category> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public
    ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }
}
