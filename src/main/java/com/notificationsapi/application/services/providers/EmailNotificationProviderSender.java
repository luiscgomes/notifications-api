package com.notificationsapi.application.services.providers;

import com.notificationsapi.application.services.interfaces.INotificationProviderSender;
import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.entities.User;
import com.notificationsapi.domain.enums.Channels;
import com.notificationsapi.domain.services.interfaces.INotificationCreator;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailNotificationProviderSender implements INotificationProviderSender {
    private final INotificationCreator notificationCreator;
    private final Logger logger;

    public EmailNotificationProviderSender(INotificationCreator notificationCreator, Logger logger) {
        if (notificationCreator == null)
            throw new IllegalArgumentException("notificationCreator must not be null");

        if (logger == null)
            throw new IllegalArgumentException("logger must not be null");

        this.notificationCreator = notificationCreator;
        this.logger = logger;
    }

    @Override
    public List<Notification> send(List<User> users, Category category, String message) {
        var emailChannelUsers = users.stream()
                .filter(u -> u.getChannels().contains(Channels.Email))
                .toList();

        if (emailChannelUsers.isEmpty()) {
            return new ArrayList<>();
        }

        logger.info("Sending notification by Email channel. Category: %s", category.getName());

        return notificationCreator.createAll(
                emailChannelUsers,
                category,
                Channels.Email,
                message);
    }
}
