package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

/**
 * Diese Klasse dient als technisches DTO für die Persistenz-Schicht.
 *
 * @param id Schlüssel als Verbindung zu Datei
 * @param name Name der Datei
 */
@Table("profil_datei_value")
public record ProfilDateiValueDto(String id, String name) {
}
