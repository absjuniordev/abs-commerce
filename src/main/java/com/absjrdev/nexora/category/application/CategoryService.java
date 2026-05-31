package com.absjrdev.nexora.category.application;

import com.absjrdev.nexora.category.domain.Category;
import com.absjrdev.nexora.category.repository.CategoryRepository;
import com.absjrdev.nexora.order.domain.Order;
import com.absjrdev.nexora.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository ;

    public
    List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }


}
