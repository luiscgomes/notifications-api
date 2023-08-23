package com.notificationsapi.application;

import com.notificationsapi.application.models.SendNotificationModel;
import com.notificationsapi.application.services.providers.PushNotificationProviderSender;
import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.entities.User;
import com.notificationsapi.domain.enums.Channels;
import com.notificationsapi.domain.services.interfaces.INotificationCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PushNotificationProviderSenderUnitTest {

    @Mock
    INotificationCreator notificationCreator;

    @Mock
    Logger logger;

    @InjectMocks
    PushNotificationProviderSender sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenThereIsNoUserWithPushNotificationChannelShouldReturnEmptyNotificationList() {
        var categoryName = "Films";
        var message = "hello";
        var category = Category
                .builder()
                .id(1L)
                .name(categoryName)
                .createdAt(LocalDateTime.now())
                .build();

        var userId = UUID.randomUUID();
        var user = User
                .builder()
                .id(userId)
                .channels(List.of(Channels.PushNotification))
                .subscribed(List.of(category))
                .build();

        var actual = sut.send(List.of(user), category, message);

        assertThat(actual).isEmpty();
    }

    @Test
    void whenThereIsUserWithPushNotificationChannelShouldReturnNotificationList() {
        var categoryName = "Films";
        var message = "hello";
        var sendNotificationModel = new SendNotificationModel(categoryName, message);
        var category = Category
                .builder()
                .id(1L)
                .name(categoryName)
                .createdAt(LocalDateTime.now())
                .build();

        var userId = UUID.randomUUID();
        var user = User
                .builder()
                .id(userId)
                .channels(List.of(Channels.Email, Channels.PushNotification))
                .subscribed(List.of(category))
                .build();

        var pushNotification = Notification
                .builder()
                .id(UUID.randomUUID())
                .notifiedAt(LocalDateTime.now())
                .user(user)
                .category(category)
                .channel(Channels.Email)
                .message(message)
                .build();

        when(notificationCreator.createAll(List.of(user), category, Channels.PushNotification, message))
                .thenReturn(List.of(pushNotification));

        var actual = sut.send(List.of(user), category, message);

        verify(notificationCreator).createAll(List.of(user), category, Channels.PushNotification, message);
        verify(logger).info(eq("Sending notification by PushNotification channel. Category: %s"), eq(categoryName));

        assertThat(actual).isEqualTo(List.of(pushNotification));
    }
}
