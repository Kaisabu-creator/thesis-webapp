package com.awesome.thesis.logic.application.service.profiles;

import com.awesome.thesis.logic.application.dto.DateiDTO;
import com.awesome.thesis.logic.application.service.fachgebiete.FachgebieteEditor;
import com.awesome.thesis.logic.application.dto.ThemaDTO;
import com.awesome.thesis.logic.domain.model.profil.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfilEditor {
    private final IProfileRepo profile;

    @Autowired
    FachgebieteEditor fachgebieteEditor;

    public ProfilEditor(IProfileRepo profile) {
        this.profile = profile;
    }

    public void addEmail(long id, String label, String wert) {
        Profil profil = get(id);
        profil.addEmail(label, wert);
        profile.update(id, profil);
    }

    public void addTel(long id, String label, String wert) {
        Profil profil = get(id);
        profil.addTel(label, wert);
        profile.update(id, profil);
    }

    public void editName(long id, String name) {
        Profil profil = get(id);
        profil.setName(name);
        profile.update(id, profil);
    }

    public void removeKontakt(long id, Kontakt kontakt) {
        Profil profil = get(id);
        profil.removeKontakt(kontakt);
        profile.update(id, profil);
    }

    public void addFachgebiet(long id, String fachgebiet) {
        Profil profil = get(id);
        profil.addFachgebiet(fachgebiet);
        fachgebieteEditor.add(fachgebiet);
        profile.update(id, profil);
    }

    public void removeFachgebiet(long id, String fachgebiet) {
        Profil profil = get(id);
        profil.removeFachgebiet(fachgebiet);
        fachgebieteEditor.remove(fachgebiet);
        profile.update(id, profil);
    }

    public void add(Profil profil) {
        if (profile.containsKey(profil.getId())) {
            profile.update(profil.getId(), profil);
        } else {
            profile.save(profil.getId(), profil);
        }
    }

    public void delete(long id) {
        profile.delete(id);
    }

    public void create(long id, String name) {
        Profil profil = new Profil(id, name);
        profile.save(id, profil);
    }

    public Profil get(long id) {
        if (profile.containsKey(id)) {
            return profile.get(id);
        }
        throw new IllegalArgumentException("No such id " + id);
    }

    public boolean contains(long id) {
        return profile.containsKey(id);
    }

    public List<Profil> getAll() {
        return profile.getAll();
    }

    public List<Profil> getFitting(Set<String> interessen) {
        if (interessen == null || interessen.isEmpty()) {
            return getAll();
        }
        return profile.getAll().stream()
                .filter(p -> p.fitsInterests(interessen))
                .toList();
    }

    public List<Profil> getMatching(Set<String> interessen) {
        if (interessen == null || interessen.isEmpty()) {
            return getAll();
        }
        return profile.getAll().stream()
                .sorted((profil1, profil2) ->
                        Long.compare(
                                profil2.compRank(interessen),
                                profil1.compRank(interessen)
                        ))
                .toList();
    }
    public void addLink(long id, String url, String urlBeschreibung) {
        Profil profil = get(id);
        ProfilLink link = new ProfilLink(url, urlBeschreibung);
        profil.addLink(link);
        profile.update(id, profil);
    }

    public void removeLink(long id, ProfilLink link) {
        Profil profil = get(id);
        profil.removeLink(link);
        profile.update(id, profil);
    }

    public void addThemaOld(long id, ThemaDTO themaOld) {
        ThemaValue thema = new ThemaValue(themaOld.id(), themaOld.name());
        Profil profil = get(id);
        profil.addThema(thema);
        profile.update(id, profil);
    }

    public void removeThemaOld(long id, ThemaDTO themaOld) {
        ThemaValue thema = new ThemaValue(themaOld.id(), themaOld.name());
        Profil profil = get(id);
        profil.removeThema(thema);
        profile.update(id, profil);
    }

    public void addDateiOld(long id, DateiDTO dateiOld) {
        DateiValue datei = new DateiValue(dateiOld.id(), dateiOld.name(), dateiOld.beschreibung());
        Profil profil = get(id);
        profil.addDatei(datei);
        profile.update(id, profil);
    }

    public void removeDateiOld(long id, DateiDTO dateiOld) {
        DateiValue datei = new DateiValue(dateiOld.id(), dateiOld.name(), dateiOld.beschreibung());
        Profil profil = get(id);
        profil.removeDatei(datei);
        profile.update(id, profil);
    }

    public void addThema(long id, String themaId, String name) {
        Profil profil = get(id);
        profil.addThema(new ThemaValue(themaId, name));
        profile.update(id, profil);
    }

    public void removeThema(long id, String themaId) {
        Profil profil = get(id);
        profil.removeThema(new ThemaValue(themaId, ""));
        profile.update(id, profil);
    }

    public void addDatei(long id, String dateiId, String name, String beschreibung) {
        Profil profil = get(id);
        profil.addDatei(new DateiValue(dateiId, name, beschreibung));
        profile.update(id, profil);
    }

    public void removeDateiOld(long id, String dateiId) {
        Profil profil = get(id);
        profil.removeDatei(new DateiValue(dateiId, "", ""));
        profile.update(id, profil);
    }
}
