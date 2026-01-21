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
  /**
   * Konstruktor für ProfilDto der die Sets vor dem Speichern kopiert.
   *
   * @param id Profil-Id
   * @param name Name des Profils
   * @param kontakte Set von Kontakten
   * @param fachgebiete Set von Fachgebieten
   * @param links Set von Links
   * @param themen Set von Themen
   * @param dateien Set von Dateien
   */
  public ProfilDto(int id, String name, Set<ProfilKontaktDto> kontakte,
                   Set<ProfilFachgebietDto> fachgebiete, Set<ProfilLinkDto> links,
                   Set<ProfilThemaValueDto> themen, Set<ProfilDateiValueDto> dateien) {
    this.id = id;
    this.name = name;
    this.kontakte = Set.copyOf(kontakte);
    this.fachgebiete = Set.copyOf(fachgebiete);
    this.links = Set.copyOf(links);
    this.themen = Set.copyOf(themen);
    this.dateien = Set.copyOf(dateien);
  }
  
  @Override
  public Set<ProfilKontaktDto> kontakte() {
    return Set.copyOf(kontakte);
  }
  
  @Override
  public Set<ProfilFachgebietDto> fachgebiete() {
    return Set.copyOf(fachgebiete);
  }
  
  @Override
  public Set<ProfilLinkDto> links() {
    return Set.copyOf(links);
  }
  
  @Override
  public Set<ProfilThemaValueDto> themen() {
    return Set.copyOf(themen);
  }
  
  @Override
  public Set<ProfilDateiValueDto> dateien() {
    return Set.copyOf(dateien);
  }
}
