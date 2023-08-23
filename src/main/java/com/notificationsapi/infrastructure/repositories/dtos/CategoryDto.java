package com.notificationsapi.infrastructure.repositories.dtos;

import com.notificationsapi.domain.entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "name")
    private String name;

    public Category toEntity() {
        return Category
                .builder()
                .id(getId())
                .createdAt(getCreatedAt())
                .name(getName())
                .build();
    }
}
