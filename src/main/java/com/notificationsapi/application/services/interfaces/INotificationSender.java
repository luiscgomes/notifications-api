package com.notificationsapi.application.services.interfaces;

import com.notificationsapi.application.models.NotificationSentModel;
import com.notificationsapi.application.models.SendNotificationModel;

import java.util.Optional;

public interface INotificationSender {
    NotificationSentModel send(SendNotificationModel sendNotificationModel);
}
