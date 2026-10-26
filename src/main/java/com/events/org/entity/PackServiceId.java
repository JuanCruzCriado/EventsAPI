package com.events.org.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class PackServiceId implements Serializable {

	private UUID packId;
	private UUID serviceId;

	public PackServiceId() {
	}

	public PackServiceId(UUID packId, UUID serviceId) {
		this.packId = packId;
		this.serviceId = serviceId;
	}

	public UUID getPackId() {
		return packId;
	}

	public void setPackId(UUID packId) {
		this.packId = packId;
	}

	public UUID getServiceId() {
		return serviceId;
	}

	public void setServiceId(UUID serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PackServiceId that)) {
			return false;
		}
		return Objects.equals(packId, that.packId) && Objects.equals(serviceId, that.serviceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(packId, serviceId);
	}
}
