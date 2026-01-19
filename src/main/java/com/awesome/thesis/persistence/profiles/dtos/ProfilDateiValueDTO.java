package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("profil_datei_value")
public record ProfilDateiValueDTO(String id, String name, String beschreibung) {
}
