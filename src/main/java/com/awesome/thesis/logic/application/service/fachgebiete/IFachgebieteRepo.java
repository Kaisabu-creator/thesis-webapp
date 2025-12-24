package com.awesome.thesis.logic.application.service.fachgebiete;

import java.util.Set;

public interface IFachgebieteRepo {
    void add(String fachgebiet);
    void delete(String fachgebiet);
    Set<String> getAll();
}
