package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Category;
import io.github.raboro.cinder.rest.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements EntityMapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getName());
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        return new Category(dto.name());
    }
}
