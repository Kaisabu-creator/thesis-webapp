package com.awesome.thesis.persistence.profiles.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("profil_link")
public record ProfilLinkDTO(String url, String text) {
}
