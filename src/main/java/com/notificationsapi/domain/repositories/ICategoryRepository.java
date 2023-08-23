package com.notificationsapi.domain.repositories;

import com.notificationsapi.domain.entities.Category;

import java.util.Optional;

public interface ICategoryRepository {
    Optional<Category> getByName(String name);
}
