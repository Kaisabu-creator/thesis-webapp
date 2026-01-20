package com.awesome.thesis.persistence.profiles.dtos;

import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Diese Klasse ist ein technisches DTO für die Persistenz-Schicht.
 *
 * @param id Github ID als natürlicher Schlüssel
 * @param name Name des Betreuenden
 * @param kontakte Set von Kontakten
 * @param fachgebiete Set von Fachgebieten
 * @param links Set von Links
 * @param themen Set von Themen
 * @param dateien Set von Dateien
 */
@Table("profil")
public record ProfilDto(@Id int id, String name, Set<ProfilKontaktDto> kontakte,
                        Set<ProfilFachgebietDto> fachgebiete, Set<ProfilLinkDto> links,
                        Set<ProfilThemaValueDto> themen, Set<ProfilDateiValueDto> dateien) {
}
