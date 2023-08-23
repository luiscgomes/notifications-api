package com.notificationsapi.domain.services.interfaces;

import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.entities.User;
import com.notificationsapi.domain.enums.Channels;

import java.util.List;

public interface INotificationCreator {
    List<Notification> createAll(List<User> users, Category category, Channels channel, String message);
}
