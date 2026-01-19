package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("profil")
public record ProfilDTO(@Id int id, String name, Set<ProfilKontaktDTO> kontakte, Set<ProfilFachgebietDTO> fachgebiete,
                        Set<ProfilLinkDTO> links, Set<ProfilThemaValueDTO> themen, Set<ProfilDateiValueDTO> dateien) {
}
