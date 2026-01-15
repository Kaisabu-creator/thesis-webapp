package com.awesome.thesis.logic.application.service.profiles;

import com.awesome.thesis.logic.domain.model.profil.Profil;

import java.util.List;

public interface IProfileRepo {
    Profil get(int id);

    boolean containsKey(int id);

    void save(int id, Profil profil);

    void update(int id, Profil profil);

    void delete(int id);

    List<Profil> getAll();
}
