package com.awesome.thesis.logic.application.service.voraussetzungen;

import com.awesome.thesis.logic.domain.model.themen.Voraussetzung;

import java.util.List;
import java.util.Set;

public interface IVoraussetzungenRepo {
    void add(Voraussetzung voraussetzung);
    void remove(Voraussetzung voraussetzung);
    Set<Voraussetzung> getAll();
}
