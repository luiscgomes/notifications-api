package com.notificationsapi.domain.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
public class Category {
    @Getter
    private final Long id;

    @Getter
    private final String name;

    @Getter
    private final LocalDateTime createdAt;
}
