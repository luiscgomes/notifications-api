package com.notificationsapi.controllers;

import com.notificationsapi.application.models.SendNotificationModel;
import com.notificationsapi.application.services.interfaces.INotificationSender;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final INotificationSender notificationSender;

    public NotificationController(INotificationSender notificationSender) {
        if (notificationSender == null)
            throw new IllegalArgumentException("notificationSender must not be null");

        this.notificationSender = notificationSender;
    }

    @PostMapping
    public ResponseEntity<?> send(@Valid @RequestBody SendNotificationModel notificationModel) {
        var notificationsSent = notificationSender.send(notificationModel);

        return ResponseEntity.ok(notificationsSent);
    }
}
