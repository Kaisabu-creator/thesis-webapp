package com.awesome.thesis.profiles;

import com.awesome.thesis.profiles.profil.Profil;
import org.springframework.stereotype.Repository;


@Repository
public class Profile {
    Database database;

    public Profile(Database database) {
        this.database = database;
    }

    public Profil get(long id) {
        return database.get(id);
    }

    public boolean containsKey(long id) {
        return database.containsKey(id);
    }

    public long save(Profil profil) {
        return database.save(profil);
    }

    public void update(long key, Profil profil) {
        database.update(key, profil);
    }

    public void delete(long id) {
        database.delete(id);
    }
}
