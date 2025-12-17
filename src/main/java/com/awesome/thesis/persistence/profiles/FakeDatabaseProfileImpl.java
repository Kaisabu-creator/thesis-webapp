package com.awesome.thesis.persistence.profiles;

import com.awesome.thesis.logic.domain.model.profil.Kontakt;
import com.awesome.thesis.logic.domain.model.profil.Profil;
import org.springframework.stereotype.Component;
import com.awesome.thesis.logic.domain.model.profil.Kontaktart;

import java.util.*;

@Component
class FakeDatabaseProfileImpl implements IDatabaseProfile {
    Map<String, Profil> map = new HashMap<>();

    public FakeDatabaseProfileImpl() {
        Profil janik = new Profil("janik");
        janik.addKontakt(new Kontakt("Email", "janik@mail.com", Kontaktart.EMAIL));
        janik.setId("182077829");
        update("1", janik);
        Profil ryota = new Profil("ryota");
        ryota.addKontakt(new Kontakt("Email", "ryota@mail.com", Kontaktart.EMAIL));
        ryota.setId("180645494");
        update("1", janik);
    }

    public Profil get(String key) {
        return map.get(key);
    }

    public String save(String id, Profil profil) {
        map.put(id, profil);
        return id;
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public void update(String key, Profil profil) {
        map.put(key, profil);
    }

    public void delete(String key) {
        map.remove(key);
    }

    public List<Profil> getAll() {
        return new ArrayList<>(map.values());
    }
}
