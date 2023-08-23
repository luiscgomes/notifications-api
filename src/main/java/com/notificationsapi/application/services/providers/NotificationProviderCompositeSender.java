package com.notificationsapi.application.services.providers;

import com.notificationsapi.application.services.interfaces.INotificationProviderSender;
import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.entities.User;
import org.slf4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("notificationProviderCompositeSender")
@Primary
public class NotificationProviderCompositeSender implements INotificationProviderSender {
    private final List<INotificationProviderSender> notificationProviderSenders;

    public NotificationProviderCompositeSender(
            List<INotificationProviderSender> notificationProviderSenders) {
        if (notificationProviderSenders == null)
            throw new IllegalArgumentException("notificationProviderSenders must not be null");

        this.notificationProviderSenders = notificationProviderSenders;
    }

    @Override
    public List<Notification> send(List<User> users, Category category, String message) {
        var notifications = new ArrayList<Notification>();

        notificationProviderSenders.forEach(n -> notifications.addAll(n.send(users, category, message)));

        return notifications;
    }
}
