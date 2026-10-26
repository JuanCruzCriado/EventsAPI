package com.events.org.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.org.entity.PackService;
import com.events.org.entity.PackServiceId;

public interface PackServiceRepository extends JpaRepository<PackService, PackServiceId> {
    List<PackService> findAllByPack_Id(UUID packId);
    boolean existsByPack_IdAndService_Id(UUID packId, UUID serviceId);
}
