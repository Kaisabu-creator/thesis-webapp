package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

/**
 * Diese Klasse dient als technisches DTO für die Persistenz-Schicht.
 *
 * @param fachgebiet String Repräsentation des Fachgebietes
 */
@Table("profil_fachgebiet")
public record ProfilFachgebietDto(String fachgebiet) {
}
