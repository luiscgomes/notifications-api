package com.notificationsapi.infrastructure.repositories;

import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.repositories.ICategoryRepository;
import com.notificationsapi.infrastructure.repositories.dtos.CategoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryRepository implements ICategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryRepository(CategoryJpaRepository categoryJpaRepository) {
        if (categoryJpaRepository == null)
            throw new IllegalArgumentException("categoryJpaRepository must not be null");

        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Optional<Category> getByName(String name) {
        return categoryJpaRepository
                .findByName(name)
                .stream()
                .findFirst()
                .map(CategoryDto::toEntity);
    }
}
