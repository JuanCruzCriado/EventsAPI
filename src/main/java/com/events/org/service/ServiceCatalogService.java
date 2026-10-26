package com.events.org.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.events.org.dto.ServiceCreateRequest;
import com.events.org.dto.ServiceResponse;
import com.events.org.entity.ServiceEntity;
import com.events.org.repository.ServiceRepository;

@Service
public class ServiceCatalogService {

    private final ServiceRepository serviceRepository;

    public ServiceCatalogService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public ServiceResponse create(ServiceCreateRequest request) {
        ServiceEntity service = new ServiceEntity();
        service.setDescription(request.description().trim());
        service.setPrice(request.price());
        return ServiceResponse.from(serviceRepository.save(service));
    }

    @Transactional(readOnly = true)
    public List<ServiceResponse> showAll() {
        return serviceRepository.findAll().stream().map(ServiceResponse::from).toList();
    }
}
