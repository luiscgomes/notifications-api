package com.notificationsapi.domain.repositories;

import com.notificationsapi.domain.entities.User;

import java.util.List;

public interface IUserRepository {
    List<User> getSubscribedTo(String category);
}
