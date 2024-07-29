package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.CategoryRepository;
import io.github.raboro.cinder.rest.dto.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryService service;

    @Autowired
    CategoryRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void addedCategoryShouldBeTheOnlyOne() {
        CategoryDTO ai = new CategoryDTO("AI");
        service.addCategory(ai);
        List<CategoryDTO> categories = service.getCategories();
        assertEquals(1, categories.size());
        assertEquals(ai, categories.get(0));
    }
}