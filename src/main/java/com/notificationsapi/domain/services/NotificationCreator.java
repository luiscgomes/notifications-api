package com.notificationsapi.domain.services;

import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.entities.User;
import com.notificationsapi.domain.enums.Channels;
import com.notificationsapi.domain.repositories.INotificationRepository;
import com.notificationsapi.domain.services.interfaces.INotificationCreator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationCreator implements INotificationCreator {
    private final INotificationRepository notificationRepository;

    public NotificationCreator(INotificationRepository notificationRepository) {
        if (notificationRepository == null)
            throw new IllegalArgumentException("notificationRepository must not be null");

        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> createAll(List<User> users, Category category, Channels channel, String message) {
        var notifications = new ArrayList<Notification>();

        users.forEach(u -> {
            var notification = Notification
                    .builder()
                    .id(UUID.randomUUID())
                    .notifiedAt(LocalDateTime.now())
                    .user(u)
                    .category(category)
                    .channel(channel)
                    .message(message)
                    .build();

            notificationRepository.save(notification);

            notifications.add(notification);
        });

        return notifications;
    }
}
