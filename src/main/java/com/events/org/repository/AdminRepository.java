package com.events.org.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.events.org.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByAdminUsr(String adminUsr);
}
