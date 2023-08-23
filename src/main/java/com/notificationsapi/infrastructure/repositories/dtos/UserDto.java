package com.notificationsapi.infrastructure.repositories.dtos;

import com.notificationsapi.domain.entities.Category;
import com.notificationsapi.domain.entities.User;
import com.notificationsapi.domain.enums.Channels;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "usercategories",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid"))
    private List<CategoryDto> subscribed;

    @ManyToMany
    @JoinTable(
            name = "userchannels",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "channelid"))
    private List<ChannelDto> channels;

    public User toEntity() {
        return User
                .builder()
                .id(this.id)
                .name(this.name)
                .createdAt(this.createdAt)
                .email(this.email)
                .phone(this.phone)
                .subscribed(this.subscribed.stream().map(CategoryDto::toEntity).toList())
                .channels(this.channels.stream().map(ChannelDto::toEnum).toList())
                .build();
    }

}
