package com.awesome.thesis.persistence.themen;
import com.awesome.thesis.logic.domain.model.themen.*;
import com.awesome.thesis.logic.application.service.themen.IThemaRepo;
import com.awesome.thesis.persistence.themen.dtos.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ThemaRepoImpl implements IThemaRepo {
    ThemenDBRepository database;

    public ThemaRepoImpl(ThemenDBRepository database) {
        this.database = database;
    }

    @Override
    public void save(Thema thema) {
        database.save(thema);
    }

    public boolean containsKey(Integer id) {
        return database.existsById(id);
    }

    @Override
    public void delete(Integer id) {
        database.deleteById(id);
    }

    @Override
    public List<Thema> getThemen() {
        return database.findAll().stream()
                .map(this::toThema)
                .toList();
    }

    @Override
    public Thema get(int id) { return toThema(database.findById(id));
    }

    @Override
    public void update(Integer id, Thema thema) {
        database.save(thema);
    }

    //Thema <--> ThemaDTO

    private Set<ThemaDateiValueDTO> toThemaDateiValueDTO(Set<ThemaDateiValue> dateiValue) {
        return dateiValue.stream()
                .map(e -> new ThemaDateiValueDTO(e.id(),e.name(), e.beschreibung()))
                .collect(Collectors.toSet());
    }

    private Set<ThemaDateiValue> toThemaDateiValue(Set<ThemaDateiValueDTO> dateiValue) {
        return dateiValue.stream()
                .map(e -> new ThemaDateiValue(e.id(),e.name(), e.beschreibung()))
                .collect(Collectors.toSet());
    }

    private Set<ThemaFachgebietDTO> toThemaFachgebietDTO(Set<ThemaFachgebiet> fachgebiet) {
        return fachgebiet.stream()
                .map(e -> new ThemaFachgebietDTO(e.fachgebiet()))
                .collect(Collectors.toSet());
    }

    private Set<ThemaFachgebiet> toThemaFachgebiet(Set<ThemaFachgebietDTO> fachgebiet) {
        return fachgebiet.stream()
                .map(e -> new ThemaFachgebiet(e.fachgebiet()))
                .collect(Collectors.toSet());
    }

    private Set<ThemaLinkDTO> toThemaLinkDTO(Set<ThemaLink> themaLink) {
        return themaLink.stream()
                .map(e -> new ThemaLinkDTO(e.url(), e.text()))
                .collect(Collectors.toSet());
    }

    private Set<ThemaLink> toThemaLink(Set<ThemaLinkDTO> themaLink) {
        return themaLink.stream()
                .map(e -> new ThemaLink(e.url(), e.text()))
                .collect(Collectors.toSet());
    }

    private Set<ThemaVoraussetzungDTO> toThemaVoraussetzungDTO(Set<ThemaVoraussetzung> themaVoraussetzung) {
        return themaVoraussetzung.stream()
                .map(e -> new ThemaVoraussetzungDTO(e.voraussetzung()))
                .collect(Collectors.toSet());
    }

    private Set<ThemaVoraussetzung> toThemaVoraussetzung(Set<ThemaVoraussetzungDTO> themaVoraussetzung) {
        return themaVoraussetzung.stream()
                .map(e -> new ThemaVoraussetzung(e.voraussetzung()))
                .collect(Collectors.toSet());
    }

    private ThemaDTO toThemaDTO(Thema thema) {
        return new ThemaDTO(thema.getId(),
                thema.getTitel(),
                thema.getBeschreibung(),
                thema.getProfilID(),
                toThemaLinkDTO(thema.getLinks()),
                toThemaVoraussetzungDTO(thema.getVoraussetzungen()),
                toThemaFachgebietDTO(thema.getFachgebiete()),
                toThemaDateiValueDTO(thema.getDateien()));
    }

    private Thema toThema(ThemaDTO themaDTO) {
        return new Thema(themaDTO.id(),
                themaDTO.titel(),
                themaDTO.beschreibung(),
                themaDTO.profilID(),
                toThemaLink(themaDTO.links()),
                toThemaVoraussetzung(themaDTO.voraussetzungen()),
                toThemaFachgebiet(themaDTO.fachgebiete()),
                toThemaDateiValue(themaDTO.dateien()));
    }
}
