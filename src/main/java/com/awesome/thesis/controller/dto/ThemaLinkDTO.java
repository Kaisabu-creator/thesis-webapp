package com.awesome.thesis.controller.dto;

import com.awesome.thesis.logic.domain.model.links.Link;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record ThemaLinkDTO(
        @NotBlank(message = "Bitte das Feld nicht leer lassen") @URL(message = "Bitte eine valide Url eingeben") String url,
        @NotBlank(message = "Bitte eine Beschreibung einfügen")String urlBeschreibung) {
}
