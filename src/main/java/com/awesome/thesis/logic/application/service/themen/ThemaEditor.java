package com.awesome.thesis.logic.application.service.themen;

import com.awesome.thesis.logic.application.dto.DateiDTO;
import com.awesome.thesis.logic.application.service.fachgebiete.FachgebieteEditor;
import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import com.awesome.thesis.logic.domain.model.themen.Thema;
import com.awesome.thesis.logic.domain.model.themen.ThemaDateiValue;
import com.awesome.thesis.logic.domain.model.themen.ThemaLink;
import com.awesome.thesis.logic.domain.model.themen.ThemaVoraussetzung;
import com.awesome.thesis.logic.domain.model.voraussetzungen.Voraussetzung;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ThemaEditor {
    IThemaRepo repository;

    private final ProfilEditor profilEditor;

    private final FachgebieteEditor fachEditor;

    public ThemaEditor(IThemaRepo repository, ProfilEditor profilEditor, FachgebieteEditor fachEditor) {
        this.repository = repository;
        this.profilEditor = profilEditor;
        this.fachEditor = fachEditor;
    }

    public void addLink(String id, String url, String urlBeschreibung) {
        Thema thema = getThema(id);
        ThemaLink link = new ThemaLink(url, urlBeschreibung);
        thema.addUrl(link);
        repository.update(id,thema);
    }

    public void removeLink(String id, ThemaLink link) {
        Thema thema = getThema(id);
        thema.removeUrl(link);
        repository.update(id, thema);
    }

    public void editTitel(int profilID, String id, String titel) {
        profilEditor.addThema(profilID, id, titel);
        Thema thema = getThema(id);
        thema.setTitel(titel);
        repository.update(id, thema);
    }

    public void editBeschreibung(String id, String beschreibung) {
        Thema thema = getThema(id);
        thema.setBeschreibung(beschreibung);
        repository.update(id, thema);
    }

    public void addThema(Thema thema, int profilID) {
        if (thema.getId() != null) {
            if (repository.containsKey(thema.getId())) {
                repository.update(thema.getId(), thema);
            }
        } else {
            thema.setId(repository.save(thema));
        }
        profilEditor.addThema(profilID, thema.getId(), thema.getTitel());
    }

    public Thema getThema(String id) {
        if (repository.containsKey(id)) {
            return repository.get(id);
        } else {
            throw new NoSuchElementException("Thema with id " + id + "not found");
        }
    }

    public List<Thema> getAll() {
        return repository.getThemen();
    }

    public boolean allowedEdit(long profilID, Thema thema) {
        return profilID == thema.getProfilID();
    }

    public void deleteThema(String id, Integer profilID) {
        profilEditor.removeThema(profilID, id);
        repository.delete(id);
    }

    public void removeAllVoraussetzung(Voraussetzung v) {
        List<Thema> themen = repository.getThemen();
        themen.forEach(t -> {
            t.removeVoraussetzung(
                    new ThemaVoraussetzung(v.voraussetzung())
            );
            repository.update(t.getId(), t);
        });
    }

    public void updateVoraussetzungen(String id, Set<Voraussetzung> set) {
        Set<ThemaVoraussetzung> voraussetzung = mapToThemaVoraussetzung(set);
        Thema thema = getThema(id);
        thema.updateVoraussetzungen(voraussetzung);
        repository.update(thema.getId(), thema);
    }

    public void addFachgebiet(String id, String fachgebiet) {
        Thema thema = getThema(id);
        thema.addFachgebiet(fachgebiet);
        fachEditor.add(fachgebiet);
        repository.update(thema.getId(), thema);
    }

    public void removeFachgebiet(String id, String fachgebiet) {
        Thema thema = getThema(id);
        thema.removeFachgebiet(fachgebiet);
        fachEditor.remove(fachgebiet);
        repository.update(thema.getId(), thema);
    }

    public void addDatei(String id, ThemaDateiValue datei) {
        Thema thema = getThema(id);
        thema.addDatei(datei);
        repository.update(id, thema);
    }

    public void removeDatei(String id, ThemaDateiValue datei) {
        Thema thema = getThema(id);
        thema.removeDatei(datei);
        repository.update(id, thema);
    }

    public Set<ThemaVoraussetzung> mapToThemaVoraussetzung(Set<Voraussetzung> voraussetzungen) {
        return voraussetzungen.stream()
                .map(e -> new ThemaVoraussetzung(e.voraussetzung()))
                .collect(Collectors.toSet());
    }

    public List<Thema> getFitting(Set<Voraussetzung> voraussetzungen, Set<String> interessen) {
        Set<ThemaVoraussetzung> themaVoraussetzungen = mapToThemaVoraussetzung(voraussetzungen);
            return getAll().stream()
                    .filter(e -> e.fitsRequirements(themaVoraussetzungen,interessen))
                    .toList();
        }

    public List<Thema> sortRang(Set<Voraussetzung> voraussetzungen, Set<String> interessen) {
        Set<ThemaVoraussetzung> themaVoraussetzungen = mapToThemaVoraussetzung(voraussetzungen);
        return getAll().stream()
                .filter(e -> e.calcRang(themaVoraussetzungen, interessen) != -1)
                .sorted(Comparator.comparingLong((Thema thema) -> thema.calcRang(themaVoraussetzungen, interessen)).reversed())
                .toList();
    }

    public Set<Voraussetzung> getVoraussetzungen(String id) {
        Thema thema = getThema(id);
        Set<ThemaVoraussetzung> voraussetzungen = thema.getVoraussetzungen();
        return voraussetzungen.stream().map(e -> new Voraussetzung(e.voraussetzung())).collect(Collectors.toSet());
    }
}
