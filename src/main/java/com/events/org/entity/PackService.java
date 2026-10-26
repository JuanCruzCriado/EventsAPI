package com.events.org.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "pack_service")
public class PackService {

	@EmbeddedId
	private PackServiceId id = new PackServiceId();

	@MapsId("packId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pack_id", nullable = false)
	private Pack pack;

	@MapsId("serviceId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "service_id", nullable = false)
	private ServiceEntity service;

	public PackService() {
	}

	public PackService(Pack pack, ServiceEntity service) {
		this.pack = pack;
		this.service = service;
	}

	public PackServiceId getId() {
		return id;
	}

	public void setId(PackServiceId id) {
		this.id = id;
	}

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public ServiceEntity getService() {
		return service;
	}

	public void setService(ServiceEntity service) {
		this.service = service;
	}
}
