package com.notificationsapi.domain.enums;

public enum Channels {
    SMS,
    Email,
    PushNotification;

    public int getValue() {
        return ordinal() + 1;
    }
}
