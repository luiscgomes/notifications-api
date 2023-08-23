package com.notificationsapi.application.services;

import com.notificationsapi.application.models.NotificationModel;
import com.notificationsapi.application.models.NotificationSentModel;
import com.notificationsapi.application.models.SendNotificationModel;
import com.notificationsapi.application.services.interfaces.INotificationProviderSender;
import com.notificationsapi.application.services.interfaces.INotificationSender;
import com.notificationsapi.domain.exceptions.CategoryNotFoundException;
import com.notificationsapi.domain.repositories.ICategoryRepository;
import com.notificationsapi.domain.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NotificationSender implements INotificationSender {
    private final IUserRepository userRepository;
    private final INotificationProviderSender notificationProviderSender;
    private final ICategoryRepository categoryRepository;

    public NotificationSender(
            IUserRepository userRepository,
            INotificationProviderSender notificationProviderSender,
            ICategoryRepository categoryRepository) {
        if (userRepository == null)
            throw new IllegalArgumentException("userRepository must not be null");

        if (notificationProviderSender == null)
            throw new IllegalArgumentException("notificationProviderSender must not be null");

        if (categoryRepository == null)
            throw new IllegalArgumentException("categoryRepository must not be null");

        this.userRepository = userRepository;
        this.notificationProviderSender = notificationProviderSender;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public NotificationSentModel send(SendNotificationModel sendNotificationModel) {
        var category = categoryRepository.getByName(sendNotificationModel.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        var users = userRepository.getSubscribedTo(sendNotificationModel.getCategory());

        if (users.isEmpty()) {
            return NotificationSentModel.builder().build();
        }

        var notifications = notificationProviderSender
                .send(users, category, sendNotificationModel.getMessage())
                .stream()
                .map(n -> NotificationModel
                        .builder()
                        .id(n.getId())
                        .userId(n.getUser().getId())
                        .category(n.getCategory().getName())
                        .notifiedAt(n.getNotifiedAt())
                        .channel(n.getChannel().name())
                        .message(n.getMessage())
                        .build()
                )
                .toList();

        return NotificationSentModel
                .builder()
                .notifications(notifications)
                .build();
    }
}
