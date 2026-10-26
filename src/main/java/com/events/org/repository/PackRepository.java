package com.events.org.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.org.entity.Pack;

public interface PackRepository extends JpaRepository<Pack, UUID> {
}
