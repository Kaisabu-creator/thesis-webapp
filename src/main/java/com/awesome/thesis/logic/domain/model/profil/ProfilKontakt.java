package com.awesome.thesis.logic.domain.model.profil;

import com.awesome.thesis.annotations.AggregateValue;

@AggregateValue
public record ProfilKontakt(String label, String wert, ProfilKontaktart kontaktart) {
    public ProfilKontakt {
        if (label.isEmpty()) {
            label = kontaktart.getLabel();
        }
    }
    public String getHref() {
        return String.format(kontaktart.getHref(), wert);
    }
}
