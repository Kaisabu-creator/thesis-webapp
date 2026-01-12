package com.awesome.thesis.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public record FachgebietDTO(@NotEmpty(message="Fachgebiet kann nicht leer sein") String fachgebiet) {
}
