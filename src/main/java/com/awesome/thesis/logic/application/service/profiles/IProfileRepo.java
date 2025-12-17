package com.awesome.thesis.logic.application.service.profiles;

import com.awesome.thesis.logic.domain.model.profil.Profil;

import java.util.List;

public interface IProfileRepo {
    Profil get(long id);

    boolean containsKey(long id);

    void save(long id, Profil profil);

    void update(long id, Profil profil);

    void delete(long id);

    List<Profil> getAll();
}
