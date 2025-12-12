package com.awesome.thesis.logic.domain.model.profil;

import com.awesome.thesis.annotations.AggregateValue;

@AggregateValue
public record Kontakt(String label, String wert, Kontaktart kontaktart) {
    public Kontakt {
        if (label.isEmpty()) {
            label = kontaktart.getLabel();
        }
    }
    public String getHref() {
        return String.format(kontaktart.getHref(), wert);
    }
}
