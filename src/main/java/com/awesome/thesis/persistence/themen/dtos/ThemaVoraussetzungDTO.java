package com.awesome.thesis.persistence.themen.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("thema_voraussetzung")
public record ThemaVoraussetzungDTO(String voraussetzung) {
}
