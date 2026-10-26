package com.events.org.dto;

import jakarta.validation.constraints.NotBlank;

public record EventTypeCreateRequest(@NotBlank String description) {
}
