package com.notificationsapi.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationModel {
    @JsonProperty("category")
    @NotBlank(message = "category is required")
    @Length(max = 100)
    private String category;

    @JsonProperty("message")
    @NotBlank(message = "message is required")
    @Length(max = 255)
    private String message;
}
