package com.notificationsapi.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class NotificationModel {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("notified_at")
    private LocalDateTime notifiedAt;

    @JsonProperty("category")
    private String category;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("message")
    private String message;
}
