package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("profil_kontakt")
public record ProfilKontaktDTO(String label, String wert, String kontaktart) {
}
