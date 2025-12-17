package com.awesome.thesis.persistence.profiles;

import com.awesome.thesis.logic.domain.model.profil.Profil;

import java.util.List;

public interface IDatabaseProfile {
    Profil get(long id);
    void save(long id, Profil profil);
    boolean containsKey(long id);
    void update(long id, Profil profil);
    void delete(long id);
    List<Profil> getAll();
}
