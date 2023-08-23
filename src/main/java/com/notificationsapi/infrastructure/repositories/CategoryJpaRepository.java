package com.notificationsapi.infrastructure.repositories;

import com.notificationsapi.infrastructure.repositories.dtos.CategoryDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface CategoryJpaRepository extends CrudRepository<CategoryDto, Long> {
    List<CategoryDto> findByName(String name);
}