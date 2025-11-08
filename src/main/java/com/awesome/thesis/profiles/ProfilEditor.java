package com.awesome.thesis.profiles;

import com.awesome.thesis.profiles.profil.Profil;
import org.springframework.stereotype.Component;

@Component
public class ProfilEditor {
    Profile profile;

    public ProfilEditor(Profile profile) {
        this.profile = profile;
    }

    public void edit(long id) {
        Profil profil = profile.get(id);
        //TODO: implemenation
    }

    public void add(Profil profil) {
        profile.save(profil);
    }

    public Profil get(long id) {
        return  profile.get(id);
    }
}
