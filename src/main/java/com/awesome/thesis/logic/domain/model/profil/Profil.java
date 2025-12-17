package com.awesome.thesis.logic.domain.model.profil;

import com.awesome.thesis.annotations.AggregateRoot;

import java.util.HashSet;
import java.util.Set;

@AggregateRoot
public class Profil {
    private final long id;
    private String name;
    private final Set<Kontakt> kontakte;

    public Profil(long id, String name) {
        this.id = id;
        this.name = name;
        this.kontakte = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Set<Kontakt> getKontakte() {
        return kontakte;
    }

    public void addKontakt(Kontakt kontakt) {
        kontakte.add(kontakt);
    }

    public void removeKontakt(Kontakt kontakt) {
        kontakte.remove(kontakt);
    }

    public void addEmail(String label, String wert) {
        Kontakt kontakt = new Kontakt(label, wert, Kontaktart.EMAIL);
        kontakte.add(kontakt);
    }
}
