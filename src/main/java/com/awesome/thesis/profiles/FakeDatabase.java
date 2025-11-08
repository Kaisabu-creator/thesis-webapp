package com.awesome.thesis.profiles;

import com.awesome.thesis.profiles.profil.Profil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FakeDatabase implements Database {
    Map<Long, Profil> map = new HashMap<>();
    long keycount = 1;

    public Profil get(long key) {
        return map.get(key);
    }

    public long save(Profil profil) {
        map.put(keycount, profil);
        keycount++;
        return keycount;
    }

    public boolean containsKey(long key) {
        return map.containsKey(key);
    }

    public void update(long key, Profil profil) {
        map.put(key, profil);
    }

    public void delete(long key) {
        map.remove(key);
    }
}
