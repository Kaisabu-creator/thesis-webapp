package com.awesome.thesis.persistence.profiles;

import com.awesome.thesis.logic.domain.model.profil.Profil;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import java.util.List;

public interface ProfileDBRepository extends CrudRepository<Profil, Integer> {
    @NonNull
    List<Profil> findAll();

    Profil findById(int id);

    @Modifying
    @Query("insert into profil (id, name) values (:id, :name)")
    void insert(@Param("id") int id, @Param("name") String name);
}
