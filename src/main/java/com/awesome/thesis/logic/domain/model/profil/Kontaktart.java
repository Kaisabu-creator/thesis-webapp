package com.awesome.thesis.logic.domain.model.profil;

import com.awesome.thesis.annotations.AggregatValue;

@AggregatValue
public enum Kontaktart {
    EMAIL("Email", "mailto:%s"),
    TEL("Phone", "tel:%");

    private final String label;
    private final String href;

    Kontaktart(String label, String href) {
        this.label = label;
        this.href = href;
    }

    public String getLabel() {
        return label;
    }

    public String getHref() {
        return href;
    }
}
