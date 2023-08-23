package com.notificationsapi.application.services.interfaces;

import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.entities.User;

import java.util.List;

public interface INotificationProviderSender {
    List<Notification> send(List<User> users, Category category, String message);
}
