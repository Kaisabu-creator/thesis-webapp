package com.awesome.thesis.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ProfilCreateDTO(@Min(value = 1, message = "ID muss eine positive, ganze Zahl sein") String id, @NotEmpty(message = "Name darf nicht leer sein") String name) {
}
