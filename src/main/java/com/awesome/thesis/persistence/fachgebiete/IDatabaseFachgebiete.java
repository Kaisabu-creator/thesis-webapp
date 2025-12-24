package com.awesome.thesis.persistence.fachgebiete;

import java.util.Set;

public interface IDatabaseFachgebiete {
    void add(String fachgebiet);
    void delete(String fachgebiet);
    Set<String> getAll();
}
