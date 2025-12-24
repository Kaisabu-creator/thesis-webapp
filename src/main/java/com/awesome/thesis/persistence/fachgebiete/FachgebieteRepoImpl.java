package com.awesome.thesis.persistence.fachgebiete;

import com.awesome.thesis.logic.application.service.fachgebiete.IFachgebieteRepo;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class FachgebieteRepoImpl implements IFachgebieteRepo {
    private final IDatabaseFachgebiete database;

    public FachgebieteRepoImpl(IDatabaseFachgebiete database) {
        this.database = database;
    }


    @Override
    public void add(String fachgebiet) {
        database.add(fachgebiet);
    }

    @Override
    public void delete(String fachgebiet) {
        database.delete(fachgebiet);
    }

    @Override
    public Set<String> getAll() {
        return database.getAll();
    }
}
