package com.events.org.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.org.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {
}
