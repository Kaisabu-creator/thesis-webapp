package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

/**
 * Diese Klasse dient als technisches DTO für die Persistenz-Schicht.
 *
 * @param label Beschreibt Kontakt
 * @param wert Enthält Kontakt
 * @param kontaktart Beschreibt Kontaktart
 */
@Table("profil_kontakt")
public record ProfilKontaktDto(String label, String wert, String kontaktart) {
}
