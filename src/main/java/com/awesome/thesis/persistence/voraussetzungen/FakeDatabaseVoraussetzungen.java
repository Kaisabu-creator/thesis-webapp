package com.awesome.thesis.persistence.voraussetzungen;

import com.awesome.thesis.logic.domain.model.themen.Voraussetzung;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FakeDatabaseVoraussetzungen implements IDatabaseVoraussetzungen {
    Set<Voraussetzung> voraussetzungen = new HashSet<>();

    public FakeDatabaseVoraussetzungen() {
        voraussetzungen.add(new Voraussetzung("Algorithmen und Datenstrukturen"));
        voraussetzungen.add(new Voraussetzung("Programmierpraktikum 2"));
        voraussetzungen.add(new Voraussetzung("Mathe für Informatik 3"));
    }

    @Override
    public void add(Voraussetzung voraussetzung) {
        voraussetzungen.add(voraussetzung);
    }

    @Override
    public void delete(Voraussetzung voraussetzung) {
        voraussetzungen.remove(voraussetzung);
    }

    @Override
    public Set<Voraussetzung> getAll() {
        return voraussetzungen;
    }
}
