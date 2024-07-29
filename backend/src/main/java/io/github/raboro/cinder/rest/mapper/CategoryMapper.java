package io.github.raboro.cinder.rest.mapper;

import io.github.raboro.cinder.entities.Category;
import io.github.raboro.cinder.rest.dto.CategoryDTO;

public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getName());
    }

    public Category toEntity(CategoryDTO dto) {
        return new Category(dto.name());
    }
}
