package com.awesome.thesis.controller.dto;

import jakarta.validation.constraints.Email;

public record EmailKontaktDTO(String label, @Email String wert) {
}
