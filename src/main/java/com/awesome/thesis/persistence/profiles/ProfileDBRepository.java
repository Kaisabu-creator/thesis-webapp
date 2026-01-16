package com.awesome.thesis.persistence.profiles;

import com.awesome.thesis.logic.domain.model.profil.Profil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import java.util.List;

public interface ProfileDBRepository extends CrudRepository<Profil, Integer> {
    @NonNull
    List<Profil> findAll();

    Profil findById(int id);
}
