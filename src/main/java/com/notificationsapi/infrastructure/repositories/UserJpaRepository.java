package com.notificationsapi.infrastructure.repositories;

import com.notificationsapi.infrastructure.repositories.dtos.UserDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserJpaRepository extends CrudRepository<UserDto, UUID> {
}
