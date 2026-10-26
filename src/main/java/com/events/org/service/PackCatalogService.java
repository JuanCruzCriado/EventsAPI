package com.events.org.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.events.org.dto.PackAddServiceRequest;
import com.events.org.dto.PackCreateRequest;
import com.events.org.dto.PackResponse;
import com.events.org.dto.ServiceResponse;
import com.events.org.entity.EventType;
import com.events.org.entity.Pack;
import com.events.org.entity.PackService;
import com.events.org.entity.ServiceEntity;
import com.events.org.repository.EventTypeRepository;
import com.events.org.repository.PackRepository;
import com.events.org.repository.PackServiceRepository;
import com.events.org.repository.ServiceRepository;

@Service
public class PackCatalogService {

    private static final String PERSONALIZED_PACK_NAME = "Personalizado";

    private final PackRepository packRepository;
    private final PackServiceRepository packServiceRepository;
    private final EventTypeRepository eventTypeRepository;
    private final ServiceRepository serviceRepository;

    public PackCatalogService(PackRepository packRepository,
                              PackServiceRepository packServiceRepository,
                              EventTypeRepository eventTypeRepository,
                              ServiceRepository serviceRepository) {
        this.packRepository = packRepository;
        this.packServiceRepository = packServiceRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public PackResponse create(PackCreateRequest request) {
        EventType eventType = eventTypeRepository.findById(request.eventTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de evento no encontrado"));

        Pack pack = new Pack();
        pack.setDescription(request.description().trim());
        pack.setEventType(eventType);
        pack = packRepository.save(pack);

        for (UUID serviceId : request.serviceIds().stream().distinct().toList()) {
            ServiceEntity service = serviceRepository.findById(serviceId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Servicio no encontrado: " + serviceId));
            packServiceRepository.save(new PackService(pack, service));
        }

        return toResponse(pack);
    }

    @Transactional
    public PackResponse addService(UUID packId, PackAddServiceRequest request, boolean isAdmin) {
        Pack pack = findPack(packId);
        if (!isPersonalized(pack) && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Solo un administrador puede modificar los servicios de este pack");
        }

        ServiceEntity service = serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado"));
        if (packServiceRepository.existsByPack_IdAndService_Id(packId, service.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El servicio ya está asociado al pack");
        }

        packServiceRepository.save(new PackService(pack, service));
        return toResponse(pack);
    }

    @Transactional(readOnly = true)
    public List<PackResponse> showAll() {
        return packRepository.findAll().stream().map(this::toResponse).toList();
    }

    private Pack findPack(UUID packId) {
        return packRepository.findById(packId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pack no encontrado"));
    }

    private boolean isPersonalized(Pack pack) {
        return PERSONALIZED_PACK_NAME.equalsIgnoreCase(pack.getDescription().trim());
    }

    private PackResponse toResponse(Pack pack) {
        List<ServiceResponse> services = packServiceRepository.findAllByPack_Id(pack.getId()).stream()
                .map(PackService::getService)
                .map(ServiceResponse::from)
                .toList();
        return PackResponse.from(pack, services);
    }
}
