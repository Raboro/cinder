package io.github.raboro.cinder.services;

import io.github.raboro.cinder.dao.CategoryRepository;
import io.github.raboro.cinder.rest.dto.CategoryDTO;
import io.github.raboro.cinder.rest.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoryDTO> getCategories() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public CategoryDTO addCategory(CategoryDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }
}
