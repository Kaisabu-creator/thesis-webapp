package com.awesome.thesis.persistence.themen.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("thema_datei_value")
public record ThemaDateiValueDTO(String id, String name, String beschreibung) {
}
