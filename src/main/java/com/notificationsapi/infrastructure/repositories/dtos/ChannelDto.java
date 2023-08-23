package com.notificationsapi.infrastructure.repositories.dtos;

import com.notificationsapi.domain.enums.Channels;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "channels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDto {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "name")
    private String name;

    Channels toEnum() {
        return Channels.valueOf(name);
    }
}
