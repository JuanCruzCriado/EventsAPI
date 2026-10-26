package com.events.org.entity;

import java.util.Arrays;

public enum EventState {
    REQUESTED(1, "Requested", "Solicitado"),
    BUDGETED(2, "Budgeted", "Presupuestado"),
    CONFIRMED(3, "Confirmed", "Confirmado"),
    CLOSED(4, "Closed", "Realizado");

    private final int code;
    private final String name;
    private final String displayName;

    EventState(int code, String name, String displayName) {
        this.code = code;
        this.name = name;
        this.displayName = displayName;
    }

    public int getCode() { return code; }
    public String getName() { return name; }
    public String getDisplayName() { return displayName; }

    public static EventState fromCode(int code) {
        return Arrays.stream(values())
                .filter(state -> state.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado de evento desconocido: " + code));
    }
}
