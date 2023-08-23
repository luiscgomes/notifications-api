package com.notificationsapi.domain.entities;

import com.notificationsapi.domain.enums.Channels;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class Notification {
    @Getter
    private UUID id;

    @Getter
    private User user;

    @Getter
    private LocalDateTime notifiedAt;

    @Getter
    private Category category;

    @Getter
    private Channels channel;

    @Getter
    private String message;
}
