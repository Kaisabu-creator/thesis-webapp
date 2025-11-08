package com.awesome.thesis.profiles;

import com.awesome.thesis.profiles.profil.Profil;

public interface Database {
    Profil get(long id);
    long save(Profil profil);
    boolean containsKey(long id);
    void update(long key, Profil profil);
    void delete(long key);
}
