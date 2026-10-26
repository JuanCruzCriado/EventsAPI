package com.events.org.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.org.entity.EventType;

public interface EventTypeRepository extends JpaRepository<EventType, UUID> {
}
