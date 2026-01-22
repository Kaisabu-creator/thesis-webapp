package com.awesome.thesis.persistence.fachgebiete.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Diese Klasse ist ein technisches DTO für die Persistenz-Schicht.
 *
 * @param name Name des Fachgebietes
 */
@Table("fachgebiet")
public record FachgebietDto(@Id String name, @Version Integer version){
}
