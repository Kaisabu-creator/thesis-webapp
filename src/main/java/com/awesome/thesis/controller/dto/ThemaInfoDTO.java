package com.awesome.thesis.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ThemaInfoDTO (
        @NotBlank(message = "Der Titel darf nicht leer gelassen werden")String titel,
        String beschreibung){
}
