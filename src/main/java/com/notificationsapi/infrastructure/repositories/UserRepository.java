package com.notificationsapi.infrastructure.repositories;

import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.User;
import com.notificationsapi.domain.repositories.IUserRepository;
import com.notificationsapi.infrastructure.repositories.dtos.CategoryDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class UserRepository implements IUserRepository {
    private final UserJpaRepository userJpaRepository;

    public UserRepository(UserJpaRepository userJpaRepository) {
        if (userJpaRepository == null)
            throw new IllegalArgumentException("userJpaRepository must not be null");

        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<User> getSubscribedTo(String category) {
        var users = new ArrayList<User>();

        userJpaRepository
                .findAll()
                .forEach(u -> {
                    List<String> categoryNames = u.getSubscribed().stream().map(c -> c.getName().toLowerCase()).toList();

                    if (categoryNames.contains(category.toLowerCase())) {
                        users.add(u.toEntity());
                    }
                });

        return users;
    }
}
