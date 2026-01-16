package com.awesome.thesis.logic.domain.model.profil;

import com.awesome.thesis.annotations.AggregateValue;

@AggregateValue
public record ProfilDateiValue(String id, String name, String beschreibung ) {
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProfilDateiValue other)) {
            return false;
        }
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
