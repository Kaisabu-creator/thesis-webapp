package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("profil_fachgebiet")
public record ProfilFachgebietDTO(String fachgebiet) {
}
