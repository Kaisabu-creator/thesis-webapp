package com.awesome.thesis.controller.dto;

import com.awesome.thesis.logic.domain.model.links.Link;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ThemaLinkDTO(
        @NotBlank(message = "Bitte das Feld nicht leer lassen") String url,
        @NotBlank(message = "Bitte eine Beschreibung einfügen")String urlBeschreibung) {
}
