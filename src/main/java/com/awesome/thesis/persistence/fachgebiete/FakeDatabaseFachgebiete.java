package com.awesome.thesis.persistence.fachgebiete;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FakeDatabaseFachgebiete implements IDatabaseFachgebiete {
    private final Set<String> set = new HashSet<String>();

    @Override
    public void add(String fachgebiet) {
        set.add(fachgebiet);
    }

    @Override
    public void delete(String fachgebiet) {
        set.remove(fachgebiet);
    }

    @Override
    public Set<String> getAll() {
        return set;
    }
}
