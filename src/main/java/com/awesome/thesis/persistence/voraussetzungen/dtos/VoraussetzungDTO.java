package com.awesome.thesis.persistence.voraussetzungen.dtos;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("voraussetzung")
public record VoraussetzungDTO(@Id String voraussetzung) {
}
