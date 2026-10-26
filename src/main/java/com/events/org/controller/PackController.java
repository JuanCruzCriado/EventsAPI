package com.events.org.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.events.org.dto.PackAddServiceRequest;
import com.events.org.dto.PackCreateRequest;
import com.events.org.dto.PackResponse;
import com.events.org.service.PackCatalogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/packs")
public class PackController {

    private final PackCatalogService packService;

    public PackController(PackCatalogService packService) {
        this.packService = packService;
    }

    @PostMapping
    public ResponseEntity<PackResponse> create(@Valid @RequestBody PackCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(packService.create(request));
    }

    @PostMapping("/{packId}/services")
    public PackResponse addService(@PathVariable UUID packId,
                                   @Valid @RequestBody PackAddServiceRequest request,
                                   Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));
        return packService.addService(packId, request, isAdmin);
    }

    @GetMapping
    public List<PackResponse> showAll() {
        return packService.showAll();
    }
}
