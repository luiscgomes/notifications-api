package com.notificationsapi.application.services;

import com.notificationsapi.application.models.NotificationSentModel;
import com.notificationsapi.application.models.SendNotificationModel;
import com.notificationsapi.application.services.interfaces.INotificationSender;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@Qualifier("notificationSenderWithErrorHandler")
public class NotificationSenderWithErrorHandler implements INotificationSender {
    private final INotificationSender notificationSender;

    private final Logger logger;

    public NotificationSenderWithErrorHandler(
            INotificationSender notificationSender,
            Logger logger) {
        if (notificationSender == null)
            throw new IllegalArgumentException("notificationSender must not be null");

        if (logger == null)
            throw new IllegalArgumentException("logger must not be null");

        this.notificationSender = notificationSender;
        this.logger = logger;
    }

    @Override
    public NotificationSentModel send(SendNotificationModel sendNotificationModel) {
        try {
            return notificationSender.send(sendNotificationModel);
        } catch (Exception ex) {
            logger.error("An error has occurred while sending notification", ex);
            throw ex;
        }
    }
}
