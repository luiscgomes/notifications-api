package com.notificationsapi.application;

import com.notificationsapi.application.models.SendNotificationModel;
import com.notificationsapi.application.services.NotificationSender;
import com.notificationsapi.application.services.interfaces.INotificationProviderSender;
import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.Notification;
import com.notificationsapi.domain.entities.User;
import com.notificationsapi.domain.enums.Channels;
import com.notificationsapi.domain.exceptions.CategoryNotFoundException;
import com.notificationsapi.domain.repositories.ICategoryRepository;
import com.notificationsapi.domain.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class NotificationSenderUnitTest {
    @Mock
    IUserRepository userRepository;

    @Mock
    INotificationProviderSender notificationProviderSender;

    @Mock
    ICategoryRepository categoryRepository;

    @InjectMocks
    NotificationSender sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCategoryNotFoundShouldReturnError() {
        var categoryName = "filmss";
        var sendNotificationModel = new SendNotificationModel();

        when(categoryRepository.getByName(categoryName)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sut.send(sendNotificationModel)).isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    void whenThereIsNotUserSubscribedToCategoryShouldReturnEmptyNotifications() {
        var categoryName = "Films";
        var message = "hello";
        var sendNotificationModel = new SendNotificationModel(categoryName, message);
        var category = Category
                .builder()
                .id(1L)
                .name(categoryName)
                .createdAt(LocalDateTime.now())
                .build();

        when(categoryRepository.getByName(categoryName)).thenReturn(Optional.of(category));
        when(userRepository.getSubscribedTo(categoryName)).thenReturn(new ArrayList<>());

        var actual = sut.send(sendNotificationModel);

        assertThat(actual.getNotifications()).isEmpty();
    }

    @Test
    void shouldSendAndReturnNotifications() {
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
                .channels(List.of(Channels.Email, Channels.SMS))
                .subscribed(List.of(category))
                .build();

        var notificationEmail = Notification
                .builder()
                .id(UUID.randomUUID())
                .notifiedAt(LocalDateTime.now())
                .user(user)
                .category(category)
                .channel(Channels.Email)
                .message(message)
                .build();

        var notificationSms = Notification
                .builder()
                .id(UUID.randomUUID())
                .notifiedAt(LocalDateTime.now())
                .user(user)
                .category(category)
                .channel(Channels.Email)
                .message(message)
                .build();

        when(categoryRepository.getByName(categoryName)).thenReturn(Optional.of(category));
        when(userRepository.getSubscribedTo(categoryName)).thenReturn(List.of(user));
        when(notificationProviderSender.send(List.of(user), category, message))
                .thenReturn(List.of(notificationEmail, notificationSms));

        var actual = sut.send(sendNotificationModel);

        assertThat(actual.getNotifications()).isNotEmpty();
        assertThat(actual.getNotifications()).hasSize(2);
    }
}
