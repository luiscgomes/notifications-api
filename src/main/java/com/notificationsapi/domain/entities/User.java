package com.notificationsapi.domain.entities;

import com.notificationsapi.domain.enums.Channels;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class User {
    private UUID id;

    private String name;

    private LocalDateTime createdAt;

    private String email;

    private String phone;

    private List<Category> subscribed;

    private List<Channels> channels;
}
