package com.absjrdev.abscommerce.category.application;

import com.absjrdev.abscommerce.category.domain.Category;
import com.absjrdev.abscommerce.category.repository.CategoryRepository;
import com.absjrdev.abscommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found. Id: " + id));
    }

    public Category insert(Category category) {
        return categoryRepository.save(category);
    }

    public  Category update(Long id , Category category) {
        Category entity = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found. Id: " + id));
        update(entity, category);

        return categoryRepository.save(entity);
    }

    public void delete(Long id) {
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Category not found. Id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private void update(Category entity, Category category) {
        entity.setName(category.getName());

    }
}
