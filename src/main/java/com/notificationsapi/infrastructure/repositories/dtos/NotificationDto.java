package com.notificationsapi.infrastructure.repositories.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "userid")
    private UUID userId;

    @Column(name = "notified_at")
    private LocalDateTime notifiedAt;

    @Column(name = "categoryid")
    private Long categoryId;

    @Column(name = "channelid")
    private Long channelId;

    @Column(name = "message")
    private String message;
}
