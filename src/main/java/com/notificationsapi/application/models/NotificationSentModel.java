package com.notificationsapi.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationSentModel {
    @JsonProperty("notifications")
    @Builder.Default
    List<NotificationModel> notifications = new ArrayList<>();
}
