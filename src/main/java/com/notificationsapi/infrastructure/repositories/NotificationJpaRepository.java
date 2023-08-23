package com.notificationsapi.infrastructure.repositories;

import com.notificationsapi.infrastructure.repositories.dtos.NotificationDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface NotificationJpaRepository extends CrudRepository<NotificationDto, UUID> {
}
