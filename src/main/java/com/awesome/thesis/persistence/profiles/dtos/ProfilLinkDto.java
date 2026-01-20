package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

/**
 * Diese Klasse dient als technisches DTO für die Persistenz-Schicht.
 *
 * @param url Url des Links
 * @param text Beschreibt Link
 */
@Table("profil_link")
public record ProfilLinkDto(String url, String text) {
}
