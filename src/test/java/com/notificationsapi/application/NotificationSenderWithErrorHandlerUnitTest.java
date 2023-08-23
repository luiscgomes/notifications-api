package com.notificationsapi.application;

import com.notificationsapi.application.models.NotificationSentModel;
import com.notificationsapi.application.models.SendNotificationModel;
import com.notificationsapi.application.services.NotificationSenderWithErrorHandler;
import com.notificationsapi.application.services.interfaces.INotificationSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NotificationSenderWithErrorHandlerUnitTest {
    @Mock
    INotificationSender notificationSender;

    @Mock
    Logger logger;

    @InjectMocks
    NotificationSenderWithErrorHandler sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallInnerNotificationSenderWhenThereIsNoError() {
        var categoryName = "Films";
        var message = "hello";
        var sendNotificationModel = new SendNotificationModel(categoryName, message);

        var notificationSentModel = new NotificationSentModel();

        when(notificationSender.send(sendNotificationModel)).thenReturn(notificationSentModel);

        var actual = sut.send(sendNotificationModel);

        verify(notificationSender).send(sendNotificationModel);
        assertThat(actual).isEqualTo(notificationSentModel);
    }

    @Test
    void shouldLogErrorWhenInnerNotificationSenderThrowsException() {
        var categoryName = "Films";
        var message = "hello";
        var sendNotificationModel = new SendNotificationModel(categoryName, message);

        when(notificationSender.send(sendNotificationModel)).thenThrow(new IllegalArgumentException("Error"));

        assertThatThrownBy(() -> sut.send(sendNotificationModel));

        verify(logger).error(eq("An error has occurred while sending notification"), any(IllegalArgumentException.class));
    }
}
