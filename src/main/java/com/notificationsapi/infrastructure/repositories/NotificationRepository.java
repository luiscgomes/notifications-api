package com.notificationsapi.infrastructure.repositories;

import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.repositories.INotificationRepository;
import com.notificationsapi.infrastructure.repositories.dtos.NotificationDto;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository implements INotificationRepository {
    private final NotificationJpaRepository notificationJpaRepository;

    public NotificationRepository(NotificationJpaRepository notificationJpaRepository) {
        if (notificationJpaRepository == null)
            throw new IllegalArgumentException("notificationJpaRepository must not be null");

        this.notificationJpaRepository = notificationJpaRepository;
    }

    @Override
    public Notification save(Notification notification) {
        var notificationDto = new NotificationDto(
                notification.getId(),
                notification.getUser().getId(),
                notification.getNotifiedAt(),
                notification.getCategory().getId(),
                (long) notification.getChannel().getValue(),
                notification.getMessage()
        );

        notificationJpaRepository.save(notificationDto);

        return notification;
    }
}
