package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

/**
 * Diese Klasse dient als technisches DTO für die Persistenz-Schicht.
 *
 * @param id Schlüssel als Verbindung zu Thema
 * @param name Name des Themas
 */
@Table("profil_thema_value")
public record ProfilThemaValueDto(Integer id, String name) {
}
