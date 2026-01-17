package com.awesome.thesis.persistence.fachgebiete;

import com.awesome.thesis.logic.application.service.fachgebiete.IFachgebieteRepo;
import com.awesome.thesis.logic.domain.model.fachgebiete.Fachgebiet;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class FachgebieteRepoImpl implements IFachgebieteRepo {
    private final FachgebieteDBRepository dbRepository;

    public FachgebieteRepoImpl(FachgebieteDBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }


    @Override
    public void add(Fachgebiet fachgebiet) {
        dbRepository.insert(fachgebiet.getName());
    }

    @Override
    public void delete(String name) {
        dbRepository.deleteById(name);
    }

    @Override
    public Set<Fachgebiet> getAll() {
        return dbRepository.findAll();
    }

    @Override
    public boolean contains(String fachgebiet) {
        return dbRepository.existsById(fachgebiet);
    }
}
