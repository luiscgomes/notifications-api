package com.notificationsapi.domain.repositories;

import com.notificationsapi.domain.entities.Notification;

public interface INotificationRepository {
    Notification save(Notification notification);
}
