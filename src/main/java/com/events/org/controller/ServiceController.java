package com.events.org.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.events.org.dto.ServiceCreateRequest;
import com.events.org.dto.ServiceResponse;
import com.events.org.service.ServiceCatalogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceCatalogService service;

    public ServiceController(ServiceCatalogService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody ServiceCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public List<ServiceResponse> showAll() {
        return service.showAll();
    }
}
