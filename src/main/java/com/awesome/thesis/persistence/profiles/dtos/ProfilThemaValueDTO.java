package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("profil_thema_value")
public record ProfilThemaValueDTO(Integer id, String name) {
}
