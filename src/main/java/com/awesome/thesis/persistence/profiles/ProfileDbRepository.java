package com.awesome.thesis.persistence.profiles;

import com.awesome.thesis.persistence.profiles.dtos.ProfilDto;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

/**
 * Diese Klasse ist das technische Repository für das {@link ProfilDto}.
 */
public interface ProfileDbRepository extends CrudRepository<ProfilDto, Integer> {
  @NonNull
  List<ProfilDto> findAll();
  
  ProfilDto findById(int id);
}
