package com.awesome.thesis.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record VoraussetzungDTO(
        @NotBlank(message = "Bitte kein leeres Feld hinzufügen") String voraussetzung) {
}
