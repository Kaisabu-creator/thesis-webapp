package com.awesome.thesis.persistence.themen.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("thema_fachgebiet")
public record ThemaFachgebietDTO(String fachgebiet) {
}
