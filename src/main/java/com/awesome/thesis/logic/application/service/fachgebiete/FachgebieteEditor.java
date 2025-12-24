package com.awesome.thesis.logic.application.service.fachgebiete;

import com.awesome.thesis.logic.application.service.profiles.IProfileRepo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FachgebieteEditor {
    private final IFachgebieteRepo repo;
    private final IProfileRepo profileRepo;

    public FachgebieteEditor(IFachgebieteRepo repo, IProfileRepo profileRepo) {
        this.repo = repo;
        this.profileRepo = profileRepo;
    }

    public void add(String fachgebiet) {
        repo.add(fachgebiet);
    }

    public Set<String> getAll() {
        return repo.getAll();
    }

    public void remove(String fachgebiet) {
        boolean unused = profileRepo.getAll().stream()
                .noneMatch(p -> p.hasFachgebiet(fachgebiet));

        if (unused) {
            repo.delete(fachgebiet);
        }
    }
}
