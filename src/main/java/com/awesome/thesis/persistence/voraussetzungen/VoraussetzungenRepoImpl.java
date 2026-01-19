package com.awesome.thesis.persistence.voraussetzungen;

import com.awesome.thesis.logic.application.service.voraussetzungen.IVoraussetzungenRepo;
import com.awesome.thesis.logic.domain.model.themen.ThemaVoraussetzung;
import com.awesome.thesis.logic.domain.model.voraussetzungen.Voraussetzung;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class VoraussetzungenRepoImpl implements IVoraussetzungenRepo {
    private VoraussetzungenDBRepository database;

    public VoraussetzungenRepoImpl(VoraussetzungenDBRepository database) {
        this.database = database;
    }


    @Override
    public void add(Voraussetzung voraussetzung) {
        database.insert(voraussetzung.getVoraussetzung());
    }

    @Override
    public void remove(Voraussetzung voraussetzung) {
        database.deleteById(voraussetzung.getVoraussetzung());
    }

    @Override
    public Set<Voraussetzung> getAll() {
        return database.findAll().stream()
                .map(e -> new Voraussetzung(e.voraussetzung()))
                .collect(Collectors.toSet());
    }
}
