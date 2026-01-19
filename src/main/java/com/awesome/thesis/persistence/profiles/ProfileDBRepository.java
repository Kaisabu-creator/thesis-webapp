package com.awesome.thesis.persistence.profiles;

import com.awesome.thesis.persistence.profiles.dtos.ProfilDTO;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import java.util.List;

public interface ProfileDBRepository extends CrudRepository<ProfilDTO, Integer> {
    @NonNull
    List<ProfilDTO> findAll();

    ProfilDTO findById(int id);

    @Modifying
    @Query("insert into profil (id, name) values (:id, :name)")
    void insert(@Param("id") int id, @Param("name") String name);
}
