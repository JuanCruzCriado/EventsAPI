package com.events.org.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EventStateConverter implements AttributeConverter<EventState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EventState state) {
        return state == null ? null : state.getCode();
    }

    @Override
    public EventState convertToEntityAttribute(Integer code) {
        return code == null ? null : EventState.fromCode(code);
    }
}
